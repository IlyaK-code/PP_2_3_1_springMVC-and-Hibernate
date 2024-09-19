package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDao;
import web.models.User;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserDao userDao;

    @Autowired
    public UsersController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping()
    public String show(Model model) {
        model.addAttribute("users", userDao.getAllUsers());
        return "users";
    }

    @GetMapping("/user")
    public String index(@RequestParam("id") Long id, Model model) {
        User user = userDao.getByIdUser(id);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userDao.save(user);
        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userDao.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/update")
    public String edit(Model model, @RequestParam("id") Long id) {
        model.addAttribute("user", userDao.getByIdUser(id));
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("user") User user, @RequestParam("id") Long id) {
        userDao.update(id, user);
        return "redirect:/users";
    }
}
