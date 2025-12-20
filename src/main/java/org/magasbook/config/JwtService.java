package org.magasbook.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.magasbook.dto.security.JwtAuthenticationDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtService {

    private static final Logger LOGGER = LogManager.getLogger(JwtService.class);
    @Value("${jwt.secret}")
    private String jwtSecret;


    // Create couple Access and Refresh token
    public JwtAuthenticationDto generateAuthToken(String numberPhone) {
        JwtAuthenticationDto jwtDto = new JwtAuthenticationDto();;
        jwtDto.setToken(generateJwtToken(numberPhone));
        jwtDto.setRefreshToken(generateJwtRefreshToken(numberPhone));
        return jwtDto;
    }

    // Create new Access token but keep old Refresh token
    public JwtAuthenticationDto refreshBaseToken(String numberPhone, String refreshToken) {
        JwtAuthenticationDto jwtDto = new JwtAuthenticationDto();;
        jwtDto.setToken(generateJwtToken(numberPhone));
        jwtDto.setRefreshToken(refreshToken);
        return jwtDto;
    }

    // Extract subject, in our case phone number, from token
    public String getNumberPhoneFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }

    // Checking token for validation, correction
    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSignInKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            return true;
        } catch (ExpiredJwtException e) {
            LOGGER.error("Expired JwtException", e);
        } catch (UnsupportedJwtException e) {
            LOGGER.error("Unsupported Exception", e);
        } catch (MalformedJwtException e) {
            LOGGER.error("Malformed Exception", e);
        } catch (SecurityException e) {
            LOGGER.error("Security Exception", e);
        } catch (Exception e) {
            LOGGER.error("Invalid token");
        }

        return false;
    }

    // Generate Access token
    public String generateJwtToken(String numberPhone) {
        Date date = Date.from(LocalDateTime.now().plusMinutes(1).atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .subject(numberPhone)
                .expiration(date)
                .signWith(getSignInKey())
                .compact();
    }

    // Generate Refresh token
    public String generateJwtRefreshToken(String numberPhone) {
        Date date = Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .subject(numberPhone)
                .expiration(date)
                .signWith(getSignInKey())
                .compact();
    }

    public SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
