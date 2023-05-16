package com.ticketing_system.controller;


import com.ticketing_system.model.Feedback;
import com.ticketing_system.model.Ticket;
import com.ticketing_system.model.User;
import com.ticketing_system.service.FeedbackService;
import com.ticketing_system.service.TicketService;
import com.ticketing_system.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/view/")
@RequiredArgsConstructor
@Slf4j
public class ViewController {


    private final UserService userService;

    private final TicketService ticketService;

    private final FeedbackService feedbackService;


    @GetMapping(value = "/home")
    public String getHomeView(){
        return "home";
    }

    @GetMapping(value = "/error")
    public String getErrorPage(){
        return "error-page";
    }

    @GetMapping(value = "/feedback")
    public String getFeedbackView(Model model){
        Feedback feedback = new Feedback();
        model.addAttribute("feedback",feedback);
        return "feedback";
    }

    @GetMapping(value = "/about")
    public String getAboutView(){
        return "about";
    }

    @GetMapping(value = "/history")
    public String getHistoryView(){
        return "our-history";
    }
    @GetMapping(value = "/signup")
    public String getSignupView(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "signup";
    }


    @GetMapping(value = "/login")
    public String getLoginView() {
        return "login";
    }

    @GetMapping(value = "/admin/dashboard")
    public String getAdminDashboardView(){
        User currentUser = userService.getCurrentUser();
        if(currentUser.getUsername().equals("admin@nyandungu.com")){
            return "admin-dashboard";
        }
        return "error-page";

    }

    @GetMapping(value = "/admin/clients")
    public String getClientsView(Model model){
        User currentUser = userService.getCurrentUser();
        if(currentUser.getUsername().equals("admin@nyandungu.com")){
            List<User> clients = userService.getAllUsers();
            model.addAttribute("client", clients);
            return "clients";
        }
        return "error-page";
    }


    @GetMapping(value = "/admin/tickets")
    public String getTicketsView(Model model){
        User currentUser = userService.getCurrentUser();
        if(currentUser.getUsername().equals("admin@nyandungu.com")){
            List<Ticket> ticket = ticketService.getAll();
            model.addAttribute("ticket", ticket);
            return "tickets";
        }
        return "error-page";
    }

    @GetMapping(value = "/admin/feedback")
    public String getAllFeedback(Model model){
        User currentUser = userService.getCurrentUser();
        if(currentUser.getUsername().equals("admin@nyandungu.com")){
            model.addAttribute("feedback",feedbackService.getAll());
            return "admin-feedback";
        }
        return "error-page";
    }

    @GetMapping(value = "/admin/profile")
    public String getAdminProfile(Model model){
        User savedUser = userService.getCurrentUser();
        model.addAttribute("user",savedUser);
        return "admin-profile";
    }

    @GetMapping(value = "/unauthorized")
    public String getUnauthorizedPage(){
        return "unauthorized";
    }


    /*Client*/

    @GetMapping(value = "/user/dashboard")
    public String getUserDashboard(){
        return "user-dashboard";
    }

    @GetMapping(value = "/user/tickets")
    public String getUserTickets(Model model, HttpSession session){
        User savedUser = userService.getCurrentUser();
        if(savedUser.getUsername().equals("admin@nyandungu.com")){
            return "error-page";
        }
        List<Ticket> tickets = ticketService.getUserTickets(savedUser.getUserId());
        Ticket ticket = new Ticket();
        model.addAttribute("tickets", tickets);
        model.addAttribute("ticket",ticket);
        return "user-tickets";
    }

    @GetMapping(value = "/user/profile")
    public String getUserProfile(Model model, HttpSession session){
        User savedUser = userService.getCurrentUser();
        model.addAttribute("user",savedUser);
        return "user-profile";
    }



}
