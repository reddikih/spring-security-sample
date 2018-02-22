package my.example.spring.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import my.example.spring.security.model.Authority;

/**
 * Created by satoshi on 2018/02/20.
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private AuthenticationTokenProcessingFilter tokenFilter;
  
  @Autowired
  private AuthenticationTokenProvider authTokenProvider;
  
  @Autowired
  private AuthenticationTokenEntryPoint authTokenEndPoint;
  
  @Override
  protected void configure(HttpSecurity http) throws Exception {
      http.csrf().disable();

      http.authorizeRequests()
          .antMatchers("/css/**", "/fonts/**", "/js/**", "/foo", "/register", "/login").permitAll()
          .antMatchers("/admin/**/*").hasAuthority(Authority.ROLE_ADMIN.toString())
          .antMatchers("/**/*").hasAuthority(Authority.ROLE_USER.toString())
          .and()
          .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
          .exceptionHandling()
          .authenticationEntryPoint(authTokenEndPoint);
      }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.authenticationProvider(authTokenProvider);
  }

}
