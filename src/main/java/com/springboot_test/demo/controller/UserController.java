package com.springboot_test.demo.controller;

import com.springboot_test.demo.model.User;
import com.springboot_test.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;


    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @PostMapping("/create")
    public String create(@RequestParam("username") String username, @RequestParam("email") String email){
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);

        userRepository.save(user);

        return "redirect:/home";
    }


}
