package my.example.spring.security.config;

import my.example.spring.security.model.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by satoshi on 2018/02/20.
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsServiceImpl userService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable() // TODO configure this except for testing
        .httpBasic()
        .and()
        .authorizeRequests()
        .antMatchers(HttpMethod.POST, "/register").permitAll()
        .antMatchers("/css/**", "/fonts/**", "/js/**", "/foo").permitAll()
        .antMatchers("/admin/**").hasRole("ADMIN")
        .anyRequest().authenticated()
//        .and()
//        .formLogin()
//        .loginPage("/login")
//        .usernameParameter("username")
//        .passwordParameter("password")
//        .permitAll()
//        .and()
//        .logout()
//        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//        .logoutSuccessUrl("/login")
//        .deleteCookies("JSESSIONID")
//        .invalidateHttpSession(true)
//        .permitAll()
    ;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // TODO
//    auth.inMemoryAuthentication()
//        .withUser("test").password("password").roles("USER");
    auth.userDetailsService(userService);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance(); // NoOpPasswordEncoder is deprecated
    //return new BCryptPasswordEncoder();
  }

}
