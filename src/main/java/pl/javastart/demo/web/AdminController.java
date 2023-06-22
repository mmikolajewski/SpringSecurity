package pl.javastart.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.javastart.demo.user.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
class AdminController {
    private final UserService userService;
    private final UserCredentialsDtoMapper userCredentialsDtoMapper;

    public AdminController(UserService userService, UserRoleRepository userRoleRepository, UserCredentialsDtoMapper userCredentialsDtoMapper) {
        this.userService = userService;
        this.userCredentialsDtoMapper = userCredentialsDtoMapper;
    }

    @GetMapping
    String adminPanel(Model model) {
        List<UserBasic> allUser = userService.findAllUsers();
        Optional<UserRole> adminRole = userService.findAdminRole();
        if (adminRole.isPresent()) {
            String role = adminRole.get().getName();
            adminRole.ifPresent(userRole -> model.addAttribute("adminRole", role));
        }
        model.addAttribute("users", allUser);
        return "admin-panel";
    }

    @GetMapping("/giveadmin")
    String giveAdmin(@RequestParam Long id) {
        userService.giveAdmin(id);
        return "redirect:/admin";
    }

    @GetMapping("/removeadmin")
    String removeAdmin(@RequestParam Long id) {
        userService.removeAdmin(id);
        return "redirect:/admin";
    }
}
