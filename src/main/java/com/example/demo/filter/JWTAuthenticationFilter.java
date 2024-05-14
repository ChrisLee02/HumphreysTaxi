package com.example.demo.filter;

import com.example.demo.provider.JwtProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            String token = parseToken(request);
            if (token == null) {
                filterChain.doFilter(request, response);
                return;
            }
            String id = jwtProvider.validate(token);
            if (id == null) {
                filterChain.doFilter(request, response);
                return;
            }

            // 사용자 별로 권한을 세분화하겠다면, 3번째 인수를 케이스별로 설정해주면 되겠다.
            // 관리자 계정의 삭제 권한을 이걸 활용해서 줄 수 있을 듯..
            AbstractAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(id, null, AuthorityUtils.NO_AUTHORITIES);
            // Details를 통해 request의 정보를 가져올 수 있지만, 빠져도 큰 문제는 없어보임..
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // context는 threadlocal에 저장된다.
            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(authentication);

            SecurityContextHolder.setContext(securityContext);

        } catch (Exception e) {
            e.printStackTrace();
        }

        filterChain.doFilter(request, response);
    }

    private String parseToken(HttpServletRequest request) {
        String authorization = request.getHeader("authorization");
        if (StringUtils.hasText(authorization) && authorization.startsWith("Bearer ")) {
            return authorization.substring(7);
        } else return null;
    }
}
