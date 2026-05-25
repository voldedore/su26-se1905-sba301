package vn.edu.fpt.sba.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECRET = "your-secure-secret-your-secure-secret-your-secure-secret";
    private static final long EXPIRES_MS = 1000 * 60 * 60; // tuoi tho la 1hr

    // Sinh secretKey
    public SecretKey key() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    // De sinh ra JWT
    public String generate(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRES_MS))
                .signWith(key())
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser().verifyWith(key()).build().parseSignedClaims(token).getPayload().getSubject();
    }

    public boolean validate(String token) {
        try {
            // Trich xuat thong tin token
            // Kiem tra valid thi return true
            extractUsername(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
