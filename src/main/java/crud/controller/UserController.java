package crud.controller;

import crud.model.User;
import crud.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String printWelcome(ModelMap model) {
        List<User> users = userService.getAllUsers();

        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping(value = "/delete")
    public String delete(Long id) {
        userService.deleteUserByID(id);
        return "redirect:/";
    }

    @GetMapping(value = "/update")
    public String update(ModelMap modelMap, Long id) {
        User user = userService.getUserByID(id);
        modelMap.addAttribute("user", user);
        return "update";
    }

    @PostMapping(value = "/save")
    public String save(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/";
    }

    @GetMapping(value = "/add")
    public String create(ModelMap modelMap) {
        User user = new User();
        modelMap.addAttribute("user", user);
        return "add";
    }

}
