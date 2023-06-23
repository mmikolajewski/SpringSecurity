package pl.javastart.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.javastart.demo.user.UserBasic;
import pl.javastart.demo.user.UserDetailsDto;
import pl.javastart.demo.user.UserPasswordDto;
import pl.javastart.demo.user.UserService;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    String user(Model model) {
        UserDetailsDto currentUserDetails = userService.getCurrentUserDetails();
        UserPasswordDto currentUserWithPassword = userService.getCurrentUserWithPassword();
        model.addAttribute("currentUser", currentUserDetails );
        model.addAttribute("userDetailsToEdit", currentUserDetails );
        model.addAttribute("userPasswordToEdit", currentUserWithPassword);

        return "user-panel";
    }
    @PostMapping ("/change-account-details")
    String changeDetails(UserDetailsDto userToEdit) {
        userService.updateUserDetails(userToEdit);

        return "redirect:/user";
    }

    @PostMapping ("/change-password")
    String changePassword(UserPasswordDto newUserPasswordDto) {
        userService.updateUserPassword(newUserPasswordDto);

        return "redirect:/user";
    }
}
