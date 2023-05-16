package com.ticketing_system.controller;


import com.ticketing_system.model.User;
import com.ticketing_system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;


    @PostMapping(value = "/create")
    public String createUser(@ModelAttribute("user") User user){
        User savedUser = userService.createUser(user);
        if (savedUser == null) {
            return "redirect:/error-page";
        }
        return "login";
    }

    @PostMapping(value = "/update")
    public String updateUser(@ModelAttribute("user") User user){
        User savedUser = userService.updateUser(user);
        if (savedUser == null) {
            return "redirect:/error-page";
        }
        return "admin-dashboard";
    }

    @PostMapping(value = "/auth")
    public String authenticateUser(@ModelAttribute("user") User user, HttpSession session){
        User savedUser = userService.authenticateUser(user);
        if (savedUser == null) {
            return "redirect:/error-page";
        }
        session.setAttribute("userId",savedUser.getUserId());
        if(savedUser.getUserType().equals("ADMIN"))
            return "admin-dashboard";
        return "user-dashboard";
    }


}
