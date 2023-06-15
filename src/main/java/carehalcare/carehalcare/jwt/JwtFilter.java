package carehalcare.carehalcare.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {
    /*
    클라이언트 요청 시 JWT 인증을 하기 위한 커스텀 필터로,
    UsernamePasswordAuthenticationFilter 이전에 실행됨
    */

    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final TokenProvider tokenProvider;

    /* 토큰의 인증정보를 SecurityContext에 저장 */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        // 1. Request Header에서 JWT 토큰 추출
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = resolveToken(httpServletRequest);
        String requestURI = httpServletRequest.getRequestURI();

        // 2. validateToken으로 토큰 유효성 검사
        if (StringUtils.hasText(token) && tokenProvider.validateToken(token)){

            // 토큰이 유효할 경우 토큰에서 Authentication 객체를 가지고 와서
            Authentication authentication = tokenProvider.getAuthentication(token);

            // SecurityContext에 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);


            log.debug("Security Context에 '{}' 인증 정보를 저장했습니다. uri: {}",
                    authentication.getName(), requestURI);
        }else{
            log.debug("유효한 JWT 토큰이 없습니다. uri: {}", requestURI);
        }
        filterChain.doFilter(request, response);
    }

    /* Request Header에서 토큰 정보 추출 */
    private String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")){
            return bearerToken.substring(7);
        }
        return null;
    }
}
