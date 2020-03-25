package shopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class user {
    @RequestMapping("/user")
    public String quit() {
        return "user";
    }
}
