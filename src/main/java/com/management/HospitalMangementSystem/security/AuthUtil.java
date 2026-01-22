package com.management.HospitalMangementSystem.security;

import com.management.HospitalMangementSystem.Entity.User;
import com.management.HospitalMangementSystem.Entity.type.AuthProviderType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@Component
public class AuthUtil {

    @Value("${jwt.secretKey}")
    private String JwtSecretKey ;

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(JwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(User user){
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("userId",user.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000*60*10))
                .signWith(getSecretKey())
                .compact();

    }

    public String getUsernameFromToken(String token) {
        Claims claims =  Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return  claims.getSubject();
    }

    public AuthProviderType getProviderTypeFromRegistrationId(String registrationId){
        return  switch (registrationId.toLowerCase()){
            case "google" -> AuthProviderType.GOOGLE;
            case "github" -> AuthProviderType.GITHUB;
            case "facebook" -> AuthProviderType.FACEBOOK;
            case "twitter" -> AuthProviderType.TWITTER;
            default -> throw new IllegalArgumentException("Unsupported OAuth2 Provider : " + registrationId);
        };
    }

    public String determineProviderIdFromOAuth2User(OAuth2User oAuth2User , String registrationId){
        String providerId = switch (registrationId.toLowerCase()){
            case "google" -> oAuth2User.getAttribute("sub");
            case "github" -> oAuth2User.getAttribute("id").toString();
            default -> {
                log.error("Unsupported OAuth2 Provider {}",registrationId);
                throw new IllegalArgumentException("Unsupported OAuth2 Provider " + registrationId);
            }
        };

        if(providerId == null || providerId.isBlank()){
            log.error("Unable to determine Provider Id with Provider: {}",registrationId);
            throw new IllegalArgumentException("Unable to determine providerId for OAuth2 login");
        }
        return providerId;
    }

    public String determineUsernameFromOAuth2User(OAuth2User oAuth2User , String registrationId , String providerId){
        // using email address
        String email = oAuth2User.getAttribute("email");
        if(email != null && !email.isBlank()){
            return email;
        }

        // now finding using OAuthProvider
        return switch (registrationId.toLowerCase()){
          case "google" -> oAuth2User.getAttribute("sub");
          case "github" -> oAuth2User.getAttribute("login");
            default -> providerId;
        };
    }

}
