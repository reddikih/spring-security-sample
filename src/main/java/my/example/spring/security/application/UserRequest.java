package my.example.spring.security.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import my.example.spring.security.model.Authority;

/**
 * Created by satoshi on 2018/02/21.
 */
public class UserRequest {

  @JsonProperty("username")
  public String username;

  @JsonProperty("password")
  public String password;

  @JsonProperty("email")
  public String email;

  @JsonProperty("orb_id")
  public String orbId;

  @JsonProperty("role")
  public Authority authority;

}
