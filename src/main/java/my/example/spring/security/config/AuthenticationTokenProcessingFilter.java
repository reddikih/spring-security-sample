package my.example.spring.security.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component
public class AuthenticationTokenProcessingFilter extends GenericFilterBean {
    final private Logger LOGGER = LoggerFactory.getLogger(AuthenticationTokenProcessingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        LOGGER.warn("we are in the token filter");

        String token = ((HttpServletRequest) request).getHeader("token");

        if (token != null && token.length() > 0) {
            LOGGER.warn("there was header called token {}", token);

            AccessToken accessToken = new AccessToken(token);
            SecurityContextHolder.getContext().setAuthentication(accessToken);
        }

        // continue thru the filter chain
        chain.doFilter(request, response);

    }

}