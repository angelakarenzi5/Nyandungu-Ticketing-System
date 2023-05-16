package com.ticketing_system.controller;



import com.ticketing_system.model.User;
import com.ticketing_system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @GetMapping(value = "/login")
    public String getLoginView(){
        return "login";
    }

    @GetMapping("/login/success")
    public String showLoginSuccess(Model model) {
        System.out.println("success login");
        model.addAttribute("message", "Authentication successful!");
        User currentUser = userService.getCurrentUser();
        if(currentUser.getUsername().equals("admin@nyandungu.com")){
            return "admin-dashboard";
        }
        return "user-dashboard";
    }
}
