package com.ticketing_system.controller;


import com.ticketing_system.model.Ticket;
import com.ticketing_system.model.User;
import com.ticketing_system.service.TicketService;
import com.ticketing_system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping(value = "/ticket")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    private final UserService userService;


    @PostMapping(value = "/create")
    public String createTicket(@ModelAttribute("ticket") Ticket ticket) throws JSONException, IOException {
        User savedUser = userService.getCurrentUser();
        ticket.setUser(savedUser);
        Ticket savedTicket = ticketService.createTicket(ticket);
        if(savedTicket != null){
            return "redirect:/view/user/tickets";
        }
        return "redirect:/error-page";
    }

    @PostMapping(value = "/approve/")
    public String approveTicket(@RequestParam("ticketId") Long ticketId){
        System.out.println(ticketId);
        ticketService.approveTicket(ticketId);
        return "redirect:/view/admin/tickets";
    }

    @PostMapping(value = "/reject/")
    public String rejectTicket(@RequestParam("ticketId") Long ticketId){
        System.out.println(ticketId);
        ticketService.rejectTicket(ticketId);
        return "redirect:/view/admin/tickets";
    }
}
