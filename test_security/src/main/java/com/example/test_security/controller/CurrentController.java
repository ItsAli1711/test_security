package com.example.test_security.controller;

import com.example.test_security.model.Users;
import com.example.test_security.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class CurrentController {
    @GetMapping("/home")
    @ResponseBody
    public String handleHome(){
                return "Welcome to Home";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String handleAdminHome()
    {
        return "Welcome Admin";
    }
    @GetMapping("/reg")
    @ResponseBody
    public String reg()
    {
        return "Welcome regh";
    }

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    private final UsersService usersService;
    @PostMapping("/reg")
    @ResponseBody
    public Users Register(@RequestBody Users user)
    {
        user.setPassword(encoder.encode(user.getPassword()));
        usersService.save(user);
        return user;
    }
}
