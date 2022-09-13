package com.revature.business.review.site.security.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.revature.business.review.site.entity.Role;
import com.revature.business.review.site.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class JWTGenerator {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private final UserRepository userRepository;


    public Map<String,String> generateTokens(final User loggedInUser){
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes());

        final String accessToken = JWT.create()
                .withSubject(loggedInUser.getUsername())
                .withExpiresAt(Instant.now().plus(1, ChronoUnit.MINUTES))
                .withClaim("roles",
                        loggedInUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);

        final String refreshToken = JWT.create()
                .withSubject(loggedInUser.getUsername())
                .withExpiresAt(Instant.now().plus(30, ChronoUnit.MINUTES))
                .sign(algorithm);

        Map<String,String> tokens = new HashMap<>();
        tokens.put("access_token", accessToken);
        tokens.put("refresh_token", refreshToken);

        return tokens;


    }
    public Map<String,String> regenerateAccessToken(final String refreshToken){
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT decodedJWT = verifier.verify(refreshToken);
        String username = decodedJWT.getSubject();

        com.revature.business.review.site.entity.User user = userRepository.findByUsername(username).get();
        List<String> roles = user.getRoles().stream().map(Role::getName).collect(Collectors.toList());
        Collection<SimpleGrantedAuthority> authorities= new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
        final String accessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(Instant.now().plus(10, ChronoUnit.MINUTES))
                .withClaim("roles",
                        user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                .sign(algorithm);
        Map<String,String> tokens = new HashMap<>();
        tokens.put("access_token", accessToken);
        tokens.put("refresh_token", refreshToken);

        return tokens;

    }


}
