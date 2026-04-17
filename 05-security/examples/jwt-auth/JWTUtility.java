

@Component
public class JwtUtil {

    private String SECRET = "mysecretkey";

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}