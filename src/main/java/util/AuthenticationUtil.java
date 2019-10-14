/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import ninja.Context;

/**
 *
 * @author john
 */
public class AuthenticationUtil {
    
    public static final String tokenHeader = "Authorization";
    
    public static boolean hasAuthenticationToken(Context context) {
        if( context.getHeader(tokenHeader) == null ) {
            return false;
        }
        else if (context.getHeader(tokenHeader).equals("")) {
            return false;
        }
        return true;
    }
    
    public static Claims parseToken(Context context, String applicationSecret) {
        Claims claims = Jwts.parser()
                    .setSigningKey(applicationSecret)
                    .parseClaimsJws(context.getHeader(tokenHeader))
                    .getBody();
        return claims;
    }
    
    public static boolean isExpired(Claims claims) {
        Date expiresOn = claims.getExpiration();
        return new Date().getTime() > expiresOn.getTime();
    }
    
    public static String generateToken(String emailAddress, String applicationSecret) {
        long ONE_MINUTE_IN_MILLIS=60000;
        long now = new Date().getTime();
        Date expires = new Date(now + (30 * ONE_MINUTE_IN_MILLIS));
        String token = Jwts.builder()
                .setExpiration(expires)
                .setSubject(emailAddress)
                .signWith(SignatureAlgorithm.HS256, applicationSecret)
                .compact();
        return token;
    }
}
