package my.example.spring.security.config;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import my.example.spring.security.model.MinimalProfile;

public class AuthenticatedProfile implements Authentication {
    final private Logger LOGGER = LoggerFactory.getLogger(AuthenticatedProfile.class);

    private final MinimalProfile minimalProfile;

    public AuthenticatedProfile(MinimalProfile minimalProfile) {
        this.minimalProfile = minimalProfile;
        LOGGER.warn("authenticated: {}", minimalProfile.getUsername());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        LOGGER.warn("in: getAuthorities {}", minimalProfile.getAuthorities());
        return minimalProfile.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        LOGGER.warn("in: getCredentials");
        return null;
    }

    @Override
    public Object getDetails() {
        LOGGER.warn("in: getDetails");
        return null;
    }

    @Override
    public Object getPrincipal() {
        LOGGER.warn("in: getPrincipal");
        return minimalProfile;
    }

    @Override
    public boolean isAuthenticated() {
        LOGGER.warn("in: isAuthenticated");
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        LOGGER.warn("in: getName");
        return minimalProfile.getUsername();
    }
}