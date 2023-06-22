package pl.javastart.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
class LoginController {

    @GetMapping("/login")
    String loginForm(Model model, @RequestParam(value = "register", required = false) String registered) {
        model.addAttribute("register", registered);
        return "login-form";
    }
}
