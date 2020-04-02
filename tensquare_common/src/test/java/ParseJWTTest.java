import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.sound.midi.Soundbank;
import java.text.SimpleDateFormat;

/**
 * @Author:Gao
 * @Date:2020-03-22 22:02
 */
public class ParseJWTTest {
    public static void main(String[] args) {
        Claims claims = Jwts.parser().setSigningKey("akaKILLAAAAAAAAAAAA")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjMiLCJzdWIiOiJBS0EiLCJpYXQiOjE1ODQ5MjUyOTYsImV4cCI6MTU4NDkyNTY1Niwicm9sZSI6ImFkbWluIn0.j2iY89WvCyuE5KwCYWd6nLg6Ib14XlxII5aFrCchs4A")
                .getBody();
        System.out.println("User Id: "+ claims.getId());
        System.out.println("User Name: "+ claims.getSubject());
        System.out.println("User Date: "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getIssuedAt()));
        System.out.println("过期时间: "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getExpiration()));
        System.out.println("过期时间: "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getExpiration()));
        System.out.println("用户角色："+ claims.get("role"));
    }
}
