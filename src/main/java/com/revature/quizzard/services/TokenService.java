package com.revature.quizzard.services;

import com.revature.quizzard.dtos.responses.Principal;
import com.revature.quizzard.util.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private JwtConfig jwtConfig;

    // since we only have one constructor, the Autowired annotation
    // is optional if we use constructor injection
    public TokenService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String generateToken(Principal subject) {

        // number of milliseconds passed since the beginning of UNIX time
        // start of UNIX time: January 1, 1970
        long now = System.currentTimeMillis();

        JwtBuilder tokenBuilder = Jwts.builder()
                                      .setId(subject.getId())
                                      .setIssuer("quizzard")
                                      .setIssuedAt(new Date(now))
                                      .setExpiration(new Date(now + jwtConfig.getExpiration()))
                                      .setSubject(subject.getUsername())
                                      .claim("role", subject.getRole())
                                      .signWith(jwtConfig.getSigAlg(), jwtConfig.getSigningKey());

        return tokenBuilder.compact();

    }

    public Principal extractRequesterDetails(String token) {

        try {

            Claims claims = Jwts.parser()
                                .setSigningKey(jwtConfig.getSigningKey())
                                .parseClaimsJws(token)
                                .getBody();

            Principal principal = new Principal();
            principal.setId(claims.getId());
            principal.setUsername(claims.getSubject());
            principal.setRole(claims.get("role", String.class));

            return principal;

        } catch (Exception e) {
            return null;
        }

    }

}
