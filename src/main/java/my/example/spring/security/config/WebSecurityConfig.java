package my.example.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by satoshi on 2018/02/19.
 */
@EnableWebSecurity
public class WebSecurityConfig implements WebMvcConfigurer {

  /**
   * UserDetailsService is just a DAO. Only manage user's data access.
   */
  @Bean
  public UserDetailsService userDetailsService() throws Exception {
    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
    return manager;
  }

}
