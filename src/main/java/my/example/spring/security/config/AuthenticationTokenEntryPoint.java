package my.example.spring.security.config;

import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
  public class AuthenticationTokenEntryPoint implements AuthenticationEntryPoint {
      @Override
      public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e)
              throws IOException, ServletException {
          httpServletResponse.setStatus(SC_FORBIDDEN);
          httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);

          String message;
          if(e.getCause() != null) {
              message = e.getCause().getMessage();
          } else {
              message = e.getMessage();
          }
          byte[] body = new ObjectMapper()
                  .writeValueAsBytes(Collections.singletonMap("error", message));
          httpServletResponse.getOutputStream().write(body);
      }
  }