package com.woowahan.junweb.contoller;

import com.woowahan.junweb.model.User;
import com.woowahan.junweb.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

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
    public String createUser(User user) {
        log.debug("created User Information : {}", user);
        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm(String userId, String password, HttpSession session) {
        return "user/login";
    }

    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session) {
        User user = userRepository.findUserByUserId(userId);
        if (!password.equals(user.getPassword())) return "redirect:/user/login_failed";
        session.setAttribute("sessionedUser", user);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/{id}/form")
    public String showUpdateForm(@PathVariable long id, HttpSession session){
        if (session.getAttribute("sessionedUser") == null) return "redirect:/";
        return "/users/form";
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable long id, User user){
        log.debug(user.toString());
        userRepository.save(user);
        return "redirect:/users/" + id;
    }
}
