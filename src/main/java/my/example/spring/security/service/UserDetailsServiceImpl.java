package my.example.spring.security.service;

import my.example.spring.security.model.UserDetailsImpl;
import my.example.spring.security.model.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by satoshi on 2018/02/20.
 */
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserDetailsRepository userDetailsRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserDetailsImpl user = userDetailsRepository.findByUsername(username);
    return user;
  }
}
