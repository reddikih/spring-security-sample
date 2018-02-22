package my.example.spring.security.exception;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Thrown if an {@link UserDetailsService} implementation cannot authenticate a {@link User} by
 * its username and password.
 */
public class InvalidCredentialsException extends AuthenticationException {
    
    final private static String MSG = "Invalid user credentials";

    /**
     * Constructs a <code>InvalidCredentialsException</code> with the specified message.
     *
     * @param msg the detail message.
     */
    public InvalidCredentialsException() {
        super(MSG);
    }

    /**
     * Constructs a {@code InvalidCredentialsException} with the specified message and root
     * cause.
     *
     * @param t root cause
     */
    public InvalidCredentialsException(Throwable t) {
        super(MSG, t);
    }
    
}
