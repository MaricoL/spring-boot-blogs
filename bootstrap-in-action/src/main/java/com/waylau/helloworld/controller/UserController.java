package com.waylau.helloworld.controller;

import com.waylau.helloworld.domain.User;
import com.waylau.helloworld.repository.UserRepository;
import com.waylau.helloworld.repository.UserRepository2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

//    @Autowired
//    private UserRepository2 userRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ModelAndView listUser(Model model) {
        model.addAttribute("userList", userRepository.findAll());
        model.addAttribute("title", "用户管理");
        return new ModelAndView("users/list", "userModel", model);
    }

    @GetMapping("/form")
    public ModelAndView form(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("title", "创建用户");
        return new ModelAndView("users/form", "userModel", model);
    }

    @PostMapping
    public ModelAndView addUser(User user) {
        userRepository.save(user);
        return new ModelAndView("redirect:/users");
    }

    @GetMapping("/modify/{id}")
    public ModelAndView modifyUser(@PathVariable Long id,Model model) {
        Optional<User> user = userRepository.findById(id);
        model.addAttribute("user", user.get());
        model.addAttribute("title", "修改用户界面");
        return new ModelAndView("users/form", "userModel", model);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return new ModelAndView("redirect:/users");
    }

}
