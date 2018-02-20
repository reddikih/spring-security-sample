package my.example.spring.security.application;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.security.Principal;
import my.example.spring.security.model.UserDetailsImpl;
import my.example.spring.security.service.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by satoshi on 2018/02/20.
 */
@RestController
public class MainController {

  private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

  @Autowired
  private UserDetailsServiceImpl userService;

  @RequestMapping(path = "/", method = GET)
  public String getIndex(Principal principal, Model model) {
    Authentication authentication = (Authentication) principal;
    UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
    model.addAttribute("user", user);
    return "/index";
  }

  @RequestMapping(path = "/foo", method = GET)
  public String getFoo() {
    return "Hello Foo\n";
  }

  @RequestMapping(path = "/bar", method = GET)
  public String getBar() {
    return "Hello Bar\n";
  }

  @RequestMapping(path = "/buzz", method = GET)
  public String getBuzz() {
    return "Hello Buzz\n";
  }

  @RequestMapping(path = "/users", method = GET)
  public Object getUser(Principal principal) {
    Authentication authentication = (Authentication) principal;
    return authentication.getPrincipal();
  }

  @RequestMapping(path = "/users/2", method = GET)
  public UserDetailsImpl getUser(@AuthenticationPrincipal UserDetailsImpl user) {
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
