package my.example.spring.security.model;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import my.example.spring.security.exception.InvalidCredentialsException;
import my.example.spring.security.exception.UnknownAcccessTokenException;

/**
 * Created by satoshi on 2018/02/20.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    final private Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetailsImpl user = userDetailsRepository.findByUsername(username);
        return user;
    }

    public void addUser(UserDetailsImpl user) {
        userDetailsRepository.save(user);
    }
    
    public String authenticateUser(String username, String password) {
        UserDetailsImpl user = userDetailsRepository.findByUsername(username);
        
        if (!user.getPassword().equals(password)) {
            throw new InvalidCredentialsException();
        } else if (user.getAccessToken() != null && user.getAccessToken().length() > 0) {
            return user.getAccessToken();
        }
        
        Instant expire = Instant.now().plus(365, ChronoUnit.DAYS);
        String token = String.format("expire-%d", expire.getEpochSecond());
        
        user.setAccessToken(token);
        user.setAccessTokenExpire(expire);
        
        userDetailsRepository.save(user);
        
        return token;
    }

    private UserDetailsImpl loadUserByAccessToken(String token) {
        return userDetailsRepository.findByAccessToken(token).orElseThrow(() -> {
            return new UnknownAcccessTokenException();
        });
    }

    public Optional<MinimalProfile> verify(String token) throws IOException, URISyntaxException {
        UserDetails details = loadUserByAccessToken(token);        
        Profile profile = new Profile(details);
        return Optional.of(new MinimalProfile(profile));
    }

}
