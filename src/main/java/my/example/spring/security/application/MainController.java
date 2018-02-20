package my.example.spring.security.application;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.security.Principal;
import my.example.spring.security.model.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by satoshi on 2018/02/20.
 */
@RestController
public class MainController {

  private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

  @RequestMapping(path = "/", method = GET)
  public String getIndex(Principal principal, Model model) {
    Authentication authentication = (Authentication) principal;
    UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
    model.addAttribute("user", user);
    return "/index";
  }

  @RequestMapping(path = "/foo", method = GET)
  public String getFoo() {
    return "Hello Foo";
  }

  @RequestMapping(path = "/bar", method = GET)
  public String getBar() {
    return "Hello Bar";
  }

}
