package my.example.spring.security.application;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest {

    @JsonProperty("username")
    public String username;

    @JsonProperty("password")
    public String password;

  }
