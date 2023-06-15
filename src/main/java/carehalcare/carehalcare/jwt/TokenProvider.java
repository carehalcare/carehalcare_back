package carehalcare.carehalcare.jwt;

import carehalcare.carehalcare.dto.user.user.TokenDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenProvider {

    private static final String AUTHORITIES_KEY = "auth";

    private final Key key;
    private final long tokenValidityInMilliseconds;

    /* 의존성 주입 */
    public TokenProvider(@Value("${jwt.secret}") String secretKey,
                         @Value("${jwt.token-validity-in-seconds}") long tokenValidityInMilliseconds){
        // secrect key를 BASE64로 decode
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.tokenValidityInMilliseconds = tokenValidityInMilliseconds;
    }

    /* Authentication를 이용해서 AccessToken, RefreshToken 생성 */
    public TokenDto generateToken(Authentication authentication){
        // 권한 가져오기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        // application.properties에서 설정했던 만료 시간
        long now = (new Date()).getTime();
        Date accessTokenExpiresIn = new Date(now + this.tokenValidityInMilliseconds);

        // AccessToken 생성
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        // RefreshToken 생성
        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now + this.tokenValidityInMilliseconds))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        return TokenDto.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    /* 토큰의 권한 정보를 이용하여 Authentication 객체 리턴 */
    public Authentication getAuthentication(String accessToken){
        // 토큰 이용 claim 생성
        Claims claims = parseClaims(accessToken);

        if (claims.get("auth") == null){
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        // claim의 권한 정보를 이용해서
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        // User 객체를 생성하고
        UserDetails principal = new User(claims.getSubject(), "", authorities);

        // User 객체와 토큰, 권한 정보를 이용해서 최종적으로 Authentication 객체 리턴
        return new UsernamePasswordAuthenticationToken(principal, accessToken, authorities);
    }

    /* 토큰의 유효성 검증 */
    public boolean validateToken(String token){
        try{
            // 토큰을 파싱해보고 발생하는 예외들 캐치
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            // 정상이면 true 리턴
            return  true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.", e);
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.", e);
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.(JWT claims string is empty)", e);
        }
        // 비정상이면 false 리턴
        return false;
    }

    private Claims parseClaims(String accessToken){
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        }catch (ExpiredJwtException e){
            return e.getClaims();
        }
    }
}
