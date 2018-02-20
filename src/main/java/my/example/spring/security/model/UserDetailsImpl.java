package my.example.spring.security.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by satoshi on 2018/02/20.
 */
@Entity
@Table(name = "user_info")
public class UserDetailsImpl implements UserDetails {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue
  private Long userId;

  ;
  @Column(name = "user_name")
  private String username;
  @Column(name = "password")
  private String password;
  @Column(name = "email_address")
  private String email;
  @Column(name = "orb_id")
  private String orbId;
  @Column(name = "authority")
  @Enumerated(EnumType.STRING)
  private Authority authority;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(authority.toString()));
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public enum Authority {ROLE_USER, ROLE_ADMIN, ROLE_MERCHANT}
}
