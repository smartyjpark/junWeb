package com.woowahan.junweb.Contoller;

import com.woowahan.junweb.Model.User;
import com.woowahan.junweb.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public String show(@PathVariable long id, Model model) {
        User user = userRepository.findOne(id);
        model.addAttribute("user", user);
        return "user/profile";
    }

    @GetMapping("")
    public String showUsersList(Model model) {
        List<User> users = (List<User>) userRepository.findAll();
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        List<User> users = (List<User>) userRepository.findAll();
        model.addAttribute("users", users);
        return "user/form";
    }

    @PostMapping("")
    public String createUser(User user){
        System.out.println(user.toString());

        userRepository.save(user);
        return "redirect:/";
    }

}
