package my.example.spring.security.config;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class AccessToken implements Authentication {
      private final String token;

      public AccessToken(String token) {
          this.token = token;
      }

      @Override
      public Collection<? extends GrantedAuthority> getAuthorities() {
          return null;
      }

      @Override
      public Object getCredentials() {
          return token;
      }

      @Override
      public Object getDetails() {
          return null;
      }

      @Override
      public Object getPrincipal() {
          return null;
      }

      @Override
      public boolean isAuthenticated() {
          return false;
      }

      @Override
      public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

      }

      @Override
      public String getName() {
          return null;
      }
  }