package org.example.restful.service;

import org.example.restful.dto.AuthResponse;
import org.example.restful.dto.UserDTO;
import org.example.restful.entity.User;
import org.example.restful.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserService userService;

    public AuthResponse authenticateUser(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();
        String jwt = tokenProvider.generateToken(user);
        UserDTO userDTO = userService.getUserDTOFromUser(user);

        return new AuthResponse(jwt, userDTO);
    }

    public AuthResponse registerUser(String username, String password, String fullName, String email) {
        User user = userService.registerUser(username, password, fullName, email);
        String jwt = tokenProvider.generateToken(user);
        UserDTO userDTO = userService.getUserDTOFromUser(user);

        return new AuthResponse(jwt, userDTO);
    }
}
