package com.example.demo.provider;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;


@Component
public class JwtProvider {

    private final Key key;

    @Value("${expirationInSec}")
    private Long expirationInSec;

    public JwtProvider(@Value("${secret-key}") String secretKey) {
        // string key를 base64로 인코딩 후, HMAC알고리즘에 적용할 Key 객체를 생성,
        // 대칭 키,, 서명과 검증을 같은 키로 진행함. 따라서 서버에만 저장한다.
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(String subject) {
        Date now = new Date();
        Date expiryTime = new Date(now.getTime() + expirationInSec * 1000);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiryTime)
                .signWith(key)
                .compact();
        // sub, iss, exp등이 body(claims)에 해당함. 이 중 sub가 unique 값을 가지는 항목. 여기다가 사용자 식별 id를 넣는다.
    }

    public String validate(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
