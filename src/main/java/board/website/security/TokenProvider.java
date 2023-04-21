package board.website.security;

import board.website.model.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Service
public class TokenProvider {
    private String secretKey = "kimhansoljjabokkykimhansoljjabokkykimhansoljjabokky";
    private byte[] secretKeyBytes = Base64.decodeBase64(secretKey);


    public String createToken(Member member, long expTime) {
        if(expTime <= 0) {
            throw new RuntimeException("토큰 만료");
        }

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
        return Jwts.builder()
                .setSubject(member.getId())
                .signWith(signingKey, signatureAlgorithm)
                .setExpiration(new Date(System.currentTimeMillis() + expTime))
                .claim("member", Member.builder()
                        .memberId(member.getMemberId())
                        .id(member.getId())
                        .email(member.getEmail())
                        .name(member.getName())
                        .role(member.getRole())
                        .nickname(member.getNickname())
                        .createDate(member.getCreateDate())
                        .loginDate(member.getLoginDate())
                        .build() )
                .compact();
    }

    public String getSubject(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKeyBytes)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}
