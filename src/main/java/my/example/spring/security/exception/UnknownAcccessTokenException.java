package my.example.spring.security.exception;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Thrown if an {@link UserDetailsService} implementation cannot identify a {@link User} by
 * its access token.
 */
public class UnknownAcccessTokenException extends AuthenticationException {
    
    final private static String MSG = "Invalid access token";

    /**
     * Constructs a <code>UnknownAcccessTokenException</code> with the specified message.
     *
     * @param msg the detail message.
     */
    public UnknownAcccessTokenException() {
        super(MSG);
    }

    /**
     * Constructs a {@code UnknownAcccessTokenException} with the specified message and root
     * cause.
     *
     * @param t root cause
     */
    public UnknownAcccessTokenException(Throwable t) {
        super(MSG, t);
    }
    
}
