package com.springboot_test.demo.controller;

import com.springboot_test.demo.model.Role;
import com.springboot_test.demo.model.Subject;
import com.springboot_test.demo.model.User;
import com.springboot_test.demo.repository.RoleRepository;
import com.springboot_test.demo.repository.SubjectRepository;
import com.springboot_test.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final SubjectRepository subjectRepository;


    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @PostMapping("/create")
    public String create(@RequestParam("username") String username, @RequestParam("email") String email, @RequestParam("role") String name, @RequestParam("subject") String subjectName) {

        Optional<Role> role = roleRepository.findByRoleNameWithJPQL(name);
        Optional<Subject> subject = subjectRepository.findByName(subjectName);

        if (role.isPresent() && subject.isPresent()) {
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setRole(role.get());
            user.getSubjects().add(subject.get());

            userRepository.save(user);
        }

        return "redirect:/home";
    }

    @GetMapping("/findAll")
    public String findAll(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userRepository.deleteById(id);
        return "redirect:/findAll";
    }

    @GetMapping("/update/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "edit";
        }
        return "redirect:/findAll";
    }

    @PostMapping("/update")
    public String update(@RequestParam("id") int id, @RequestParam("username") String username, @RequestParam("email") String email, @RequestParam("subject") String subjectName) {

        Optional<User> user = userRepository.findById(id);
        Optional<Subject> subject = subjectRepository.findByName(subjectName);
        if (user.isPresent() && subject.isPresent()) {
            user.get().setUsername(username);
            user.get().setEmail(email);
            user.get().getSubjects().add(subject.get());
            userRepository.save(user.get());
            return "redirect:/findAll";
        }
        return "redirect:/findAll";
    }


}
