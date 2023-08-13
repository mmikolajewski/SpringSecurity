package pl.javastart.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.javastart.demo.user.UserDto;
import pl.javastart.demo.user.UserService;

@Controller
public class RegistrationController {
    private final UserService userService;
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    String registerForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "registration-form";
    }

    @PostMapping("/register")
    String register(UserDto userDto, RedirectAttributes ra) {
        userService.register(userDto);
        ra.addAttribute("register", "success");
        return "redirect:/login";
    }
}
