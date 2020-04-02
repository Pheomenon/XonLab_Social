import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @Author:Gao
 * @Date:2020-03-22 21:53
 */
public class JWTTest {
    public static void main(String[] args) {
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("123")
                .setSubject("AKA")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"akaKILLAAAAAAAAAAAA")
                .setExpiration(new Date(new Date().getTime()+360000))
                .claim("role","admin");
        //claim自定义信息

        System.out.println(jwtBuilder.compact());
    }
}
