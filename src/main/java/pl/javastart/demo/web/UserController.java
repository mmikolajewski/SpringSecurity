package pl.javastart.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.javastart.demo.user.UserBasic;
import pl.javastart.demo.user.UserService;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    String user(Model model) {
        UserBasic currentUser = userService.getCurrentUser();
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("userToEdit", currentUser);

        return "user-panel";
    }
    @PostMapping ("/change-account-details")
    String changeDetails(UserBasic userToEdit) {
        userService.updateUserDetails(userToEdit);

        return "redirect:/user";
    }
}
