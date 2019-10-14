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
public class AuthorizationUtil {
    
    public static final String tokenHeader = "Bearer";
    
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
    
}
