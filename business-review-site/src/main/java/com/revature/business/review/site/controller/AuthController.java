package com.revature.business.review.site.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.business.review.site.entity.User;
import com.revature.business.review.site.security.token.JWTGenerator;
import com.revature.business.review.site.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JWTGenerator jwtGenerator;


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody final User user) {
        authService.registerUser(user);
        return ResponseEntity.ok("You are successfully registered!!!");

    }

    @PostMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                final String refreshToken = authorizationHeader.substring("Bearer ".length());
                Map<String, String> tokens = jwtGenerator.regenerateAccessToken(refreshToken);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);


            }catch (Exception e){
                Map<String,String> error = new HashMap<>();
                error.put("error_message", e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                response.setStatus(FORBIDDEN.value());
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }
    }




}
