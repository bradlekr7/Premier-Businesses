package com.revature.business.review.site.security.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Arrays.stream;

@Slf4j
@Component
public class JWTVerifier {

    @Value("${jwt.secret}")
    private String tokenSecret;

    public void verifyToken(final String token){
        log.info("verifying the user token");
        Algorithm algorithm = Algorithm.HMAC256(tokenSecret.getBytes());
        com.auth0.jwt.JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String username = decodedJWT.getSubject();
        String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        stream(roles).forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        log.info("User token verified successfully !!!");

    }

}
