package my.example.spring.security.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class MinimalProfile {
    private final String username;
    private final Collection<? extends GrantedAuthority> authorities;

    public MinimalProfile(Profile profile) {
        username = profile.getUsername();
        authorities = profile.getAuthorities();
    }

    public String getUsername() {
        return username;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
