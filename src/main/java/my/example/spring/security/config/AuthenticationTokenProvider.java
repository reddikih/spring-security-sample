package my.example.spring.security.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import my.example.spring.security.exception.AuthenticationTokenException;
import my.example.spring.security.model.MinimalProfile;
import my.example.spring.security.model.UserDetailsServiceImpl;

@Component
  public class AuthenticationTokenProvider implements AuthenticationProvider {
      private UserDetailsServiceImpl userService;

      @SuppressWarnings("unused")
      public AuthenticationTokenProvider() {
          this(null);
      }

      @Autowired
      public AuthenticationTokenProvider(UserDetailsServiceImpl userService) {
          this.userService = userService;
      }

      @Override
      public Authentication authenticate(Authentication authentication) throws AuthenticationException {
          try {
              Optional<MinimalProfile> possibleProfile = userService.verify((String) authentication.getCredentials());
              return new AuthenticatedProfile(possibleProfile.get());
          } catch (Exception e) {
              throw new AuthenticationTokenException("Failed to verify token", e);
          }
      }

      @Override
      public boolean supports(Class<?> authentication) {
          return AccessToken.class.equals(authentication);
      }
  }