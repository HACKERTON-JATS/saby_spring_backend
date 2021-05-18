package com.jats.savy.savy.security;

import com.jats.savy.savy.exception.InvalidTokenException;
import com.jats.savy.savy.security.auth.AuthDetails;
import com.jats.savy.savy.security.auth.AuthDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    @Value("${auth.jwt.secret")
    private String secret;

    @Value("${auth.jwt.exp.access}")
    private Long accessExp;

    private static final String PREFIX = "Bearer";
    private static final String HEADER = "Authorization";

    private final AuthDetailsService authDetailsService;

    public String generateAccessToken(String id) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, secret)
                .setExpiration(new Date(System.currentTimeMillis() + accessExp * 1000))
                .setIssuedAt(new Date())
                .setSubject(id)
                .compact();
    }

    public String extractToken(HttpServletRequest request) {
        String bearer = request.getHeader(HEADER);

        if (bearer != null && bearer.startsWith(PREFIX))
            return bearer.substring(7);
        return null;
    }

    public boolean validateToken(String token) {
        try {
            return getBody(token).getExpiration().after(new Date());
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }

    public Authentication getAuthentication(String token) {
        AuthDetails authDetails = authDetailsService.loadUserByUsername(getId(token));
        return new UsernamePasswordAuthenticationToken(authDetails, "", authDetails.getAuthorities());
    }

    private String getId(String token) {
        try {
            return getBody(token).getSubject();
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }

    private Claims getBody(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

}
