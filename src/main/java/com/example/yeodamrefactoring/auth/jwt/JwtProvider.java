package com.example.yeodamrefactoring.auth.jwt;

import com.example.yeodamrefactoring.auth.dto.ResTokenDto;
import com.example.yeodamrefactoring.global.Dao.RedisDao;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final UserDetailsService userDetailsService;
    private final RedisDao redisDao;

    @Value("${spring.jwt.secret-key}")
    private String secretKey;

    @Value("${spring.jwt.access-token-valid-time}")
    private long accessTokenValidTime;

    @Value("${spring.jwt.refresh-token-valid-time}")
    private long refreshTokenValidTime;

    private static Key signingKey;

    @PostConstruct
    public void init() {
        signingKey = Keys.hmacShaKeyFor(secretKey.getBytes());
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    private String getSubject(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getSubject(token));
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public ResTokenDto generateToken(Authentication authentication) {
        Claims claims = Jwts.claims().setSubject(authentication.getPrincipal().toString());
        claims.put("role", authentication.getAuthorities().toString());
        Date now = new Date();
        String accessToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject("access-token")
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + accessTokenValidTime))
                .signWith(signingKey,SignatureAlgorithm.HS256)
                .compact();

        String refreshToken = Jwts.builder()
                .setHeaderParam("typ","jwt")
                .setHeaderParam("alg","HS256")
                .setSubject("refresh-token")
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+refreshTokenValidTime))
                .signWith(signingKey,SignatureAlgorithm.HS256)
                .compact();

        return new ResTokenDto(accessToken, refreshToken);
    }

    public boolean isValidateToken(String token) {
        try {
            if (isBlacklisted(token)){
                return false;
            }

            Claims claims = getClaimsFromToken(token);
            return !claims.getExpiration().before(new Date());
        } catch (JwtException | NullPointerException e) {
            return false;
        }
    }

    public long getTokenValidTime(String token) {
        return getClaimsFromToken(token).getExpiration().getTime();
    }

    private boolean isBlacklisted(String token) {
        return redisDao.hasKey(token);
    }


}
