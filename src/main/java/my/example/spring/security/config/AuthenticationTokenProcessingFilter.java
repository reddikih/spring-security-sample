package my.example.spring.security.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class AuthenticationTokenProcessingFilter extends OncePerRequestFilter {
    final private Logger LOGGER = LoggerFactory.getLogger(AuthenticationTokenProcessingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String token = httpRequest.getHeader("token");
        
        if (token != null && token.length() > 0) {
            AccessToken accessToken = new AccessToken(token);
            SecurityContextHolder.getContext().setAuthentication(accessToken);
        }

        // continue thru the filter chain
        filterChain.doFilter(request, response);

    }

}