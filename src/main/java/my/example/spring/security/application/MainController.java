package my.example.spring.security.application;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my.example.spring.security.model.MinimalProfile;
import my.example.spring.security.model.UserDetailsImpl;
import my.example.spring.security.model.UserDetailsServiceImpl;

/**
 * Created by satoshi on 2018/02/20.
 */
@RestController
public class MainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private UserDetailsServiceImpl userService;

    @RequestMapping(path = "/foo", method = GET)
    public String getFoo() {
        return "Hello Foo\n";
    }

    @RequestMapping(path = "/login", method = GET)
    public String login() {
        return "Login succeeded\n";
    }

    @RequestMapping(path = "/users", method = GET)
    public MinimalProfile getUser(@AuthenticationPrincipal MinimalProfile user) {
        LOGGER.warn("we made it here!!! {}", user);
        return user;
    }

    // TODO The HttpSecurity is allow only GET method to permitAll().
    // TODO I should change the GET method to POST with permitAll()
    @RequestMapping(path = "/register", method = GET, consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserRequest req) {
        UserDetailsImpl user = new UserDetailsImpl();
        user.setUsername(req.username);
        user.setPassword(req.password);
        user.setEmail(req.email);
        user.setOrbId(req.orbId);
        user.setAuthority(req.authority);
        userService.addUser(user);
    }

}
