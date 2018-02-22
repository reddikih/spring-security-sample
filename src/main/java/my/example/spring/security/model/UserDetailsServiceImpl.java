package my.example.spring.security.model;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

    public Optional<MinimalProfile> verify(String token) throws IOException, URISyntaxException {
        LOGGER.warn("look for: {}", token);
        UserDetails details = loadUserByUsername(token);
        LOGGER.warn("details: {}", details.getUsername());
        Profile profile = new Profile(details);
        LOGGER.warn("profile: {}", profile.getUsername());
        return Optional.of(new MinimalProfile(profile));
    }

}
