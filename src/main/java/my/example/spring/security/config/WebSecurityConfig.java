package my.example.spring.security.config;

import my.example.spring.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created by satoshi on 2018/02/20.
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//  @Autowired
//  private UserDetailsServiceImpl userService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .httpBasic()
        .and()
        .authorizeRequests()
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
    auth.inMemoryAuthentication()
        .withUser("test").password("password").roles("USER");
    //auth.userDetailsService(userService);
  }

}
