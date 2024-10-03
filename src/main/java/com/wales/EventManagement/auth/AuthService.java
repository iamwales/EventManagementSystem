package com.wales.EventManagement.auth;

import com.wales.EventManagement.config.JwtService;
import com.wales.EventManagement.role.RoleRepository;
import com.wales.EventManagement.user.User;
import com.wales.EventManagement.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationResponse registerUser(String username, String password, String firstname, String lastname) {

        if (userRepository.findByEmail(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        var userRole = roleRepository.findByName("USER")
                // todo - better exception handling
                .orElseThrow(() -> new IllegalStateException("ROLE USER was not initialized"));

        var user = User.builder()
                .email(username)
                .password(passwordEncoder.encode(password))
                .firstname(firstname)
                .lastname(lastname)
                .accountLocked(false)
                .enabled(true)
                .roles(List.of(userRole))
                .build();
        user.setEmail(username);
        user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user);

        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );

        var claims = new HashMap<String, Object>();
        var newUser = ((User)auth.getPrincipal());

        claims.put("fullName", newUser.getFullName());
        var jwtToken = jwtService.generateToken(claims, newUser);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse login(String username, String password) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );

        var claims = new HashMap<String, Object>();
        var user = ((User)auth.getPrincipal());


        claims.put("fullName", user.getFullName());
        var jwtToken = jwtService.generateToken(claims, user);


        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
