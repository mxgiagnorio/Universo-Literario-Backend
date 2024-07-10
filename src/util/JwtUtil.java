package util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import model.User;

import java.util.Date;

public class JwtUtil {
    private static final String SECRET = "tu_secreto_muy_seguro";
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days

    public static String generateToken(User user) {
        try {
            return JWT.create()
                    .withSubject(user.getUsername())
                    .withClaim("userId", user.getId())
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .sign(Algorithm.HMAC256(SECRET));
        } catch (JWTCreationException exception) {
            // Invalid Signing configuration / Couldn't convert Claims.
            throw new RuntimeException("Error creating JWT", exception);
        }
    }

    public static DecodedJWT verifyToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(SECRET))
                    .build()
                    .verify(token);
        } catch (JWTVerificationException exception) {
            // Invalid signature/claims
            return null;
        }
    }
}