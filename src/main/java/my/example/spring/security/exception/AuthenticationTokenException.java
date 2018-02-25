package my.example.spring.security.exception;

public class AuthenticationTokenException extends RuntimeException {
    final private String string; 
      
    public AuthenticationTokenException(String string, Exception e) {
        super(e);        
        this.string = string;
    }
      
  }