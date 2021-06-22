package com.codebaobao.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Data
@ConfigurationProperties(prefix = "jwt")
@Component
public class JwtTokenUtil {
    private String secret;

    // 过期时间 毫秒
    private Long expiration;

    private String header;


    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    public String generateToken(final Map<String, Object> claims) {
        final Date expirationDate = new Date(System.currentTimeMillis() + this.expiration);
        final JwtBuilder builder = Jwts.builder();
        if (Objects.nonNull(claims)) {
            builder.setClaims(claims);
        }
        return builder
                .setSubject("codebaobao")
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, this.secret)
                .compact();
    }


    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    public Claims getClaimsFromToken(final String token) {
        Claims claims;
        String tempToken=token;
        try {
            if(token.startsWith("Bearer ")){
                tempToken = token.substring("Bearer ".length());
            }

            claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(tempToken).getBody();
        } catch (final Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public Boolean isTokenExpired(final String token) {
        try {
            final Claims claims = getClaimsFromToken(token);
            if(claims==null){
                return true;
            }
            final Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (final Exception e) {
            return true;
        }
    }

    /**
     * 刷新令牌
     *
     * @param token 原令牌
     * @return 新令牌
     */
    public String refreshToken(final String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(Claims.ISSUED_AT, new Date());
            refreshedToken = generateToken(claims);
        } catch (final Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }


//    JwtTokenUtil(final String secret, final Long expiration, final String header) {
//        this.secret = secret;
//        this.expiration = expiration;
//        this.header = header;
//    }
//    public static void main(final String[] args) {
//        final JwtTokenUtil ju = new JwtTokenUtil("csdlelectronic", 36000L, "Authorization");
//        final String s = ju.generateToken(null);
//        System.out.println("生成token : " + s);
//        System.out.println("校验" + ju.isTokenExpired("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMjMiLCJleHAiOjE2MDYwMzcwODl9.igQMd5YkJLKCLakoGo6z2PAmLot9BsyAacingwKvxEIyJEJ6lR26yfKLabckVH0uZZJUtH1ft7cIyljfs5jkXg123"));
//    }


}
