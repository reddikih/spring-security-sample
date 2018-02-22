package my.example.spring.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
//      http.csrf().disable();

      http.authorizeRequests()
          .antMatchers("/css/**", "/fonts/**", "/js/**", "/foo", "/register", "/login").permitAll()
          .antMatchers("/admin/**/*").hasAuthority("ROLE_ADMIN")
          .antMatchers("/**/*").hasAuthority("ROLE_USER")
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
