package com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.insfrastructure.config.security;

import com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.domain.model.User;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    private final String secret = "secret";
    private long jwtExpirationMs = 3600000;
    //tokensGenerator
    public String tokensGenerator(User user){
        String roles = user.getGrantedPermissions()//Gets the collection of GrantedPermission objects associated with the user. Each object in this collection represents a relationship between the user and a role.
                .stream()
                .map(permission -> permission.getRole()
                        .getRoleName().name())
                .reduce((a, b) -> a + "," + b).get();//The reduce operation takes all the resulting strings from the map and combines them into one, concatenating them and separating them with commas.
                                           //For example, if the user has roles "ROLE_USER", "ROLE_ADMIN", the result will be "ROLE_USER,ROLE_ADMIN

        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("roles", roles)
                .setIssuedAt(new java.util.Date())
                .setExpiration(new java.util.Date((new java.util.Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, secret).compact();//The resulting token is returned as a String and is used in communication between the front-end and back-end to authenticate and authorize requests.
    }
    public boolean validateToken(String token) {
        try {
            // Attempts to parse the JWT token using the secret key
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true; // If no exceptions occur, the token is valid
        } catch (JwtException e) {
            // Catches any JWT-related exceptions, such as invalid signature or expiration
            return false; // Token is not valid
        }
    }

    public String getUserNameFromToken(String token) {
        // Extracts the username (subject) from the JWT token
        return Jwts.parser()
                .setSigningKey(secret) // Uses the secret key to validate the token
                .parseClaimsJws(token) // Parses the token and retrieves the claims
                .getBody().getSubject(); // Gets the subject (username)
    }

    public String getRolesFromToken(String token) {
        // Extracts the roles from the JWT token
        return Jwts.parser()
                .setSigningKey(secret) // Uses the secret key to validate the token
                .parseClaimsJws(token) // Parses the token and retrieves the claims
                .getBody().get("roles").toString(); // Gets the roles as a string
    }

}
