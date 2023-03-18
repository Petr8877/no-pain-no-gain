package nopainnogain.auditservice.configuration;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import nopainnogain.auditservice.core.exception.SingleErrorResponse;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "25432A462D4A614E645267556B58703273357638782F413F4428472B4B625065";

    public String getUsername(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(getSingInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public String getRole(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(getSingInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return (String) claims.get("role");
    }

    public boolean validate(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSingInKey()).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException ex) {
            throw new SingleErrorResponse("Invalid JWT signature - " + ex.getMessage());
        } catch (MalformedJwtException ex) {
            throw new SingleErrorResponse("Invalid JWT token - " + ex.getMessage());
        } catch (ExpiredJwtException ex) {
            throw new SingleErrorResponse("Expired JWT token - " + ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            throw new SingleErrorResponse("Unsupported JWT token - " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            throw new SingleErrorResponse("JWT claims string is empty - " + ex.getMessage());
        }
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSingInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSingInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
