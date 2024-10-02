package com.wales.EventManagement.auth;

import com.wales.EventManagement.user.User;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@Controller
public class AuthController {

    private final AuthService authService;

    @MutationMapping
    public AuthenticationResponse register(@Argument String username, @Argument String password) {
        return authService.registerUser(username, password);
    }

    @MutationMapping
    public AuthenticationResponse login(@Argument String username, @Argument String password) {
        return authService.login(username, password);
    }

}
