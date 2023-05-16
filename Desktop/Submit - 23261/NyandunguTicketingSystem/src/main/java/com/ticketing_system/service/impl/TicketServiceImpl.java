package com.ticketing_system.service.impl;

import com.ticketing_system.Util.SendRequestUtil;
import com.ticketing_system.Util.VerifyCodeUtil;
import com.ticketing_system.model.Ticket;
import com.ticketing_system.model.User;
import com.ticketing_system.repo.TicketRepo;
import com.ticketing_system.repo.UserRepo;
import com.ticketing_system.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepo ticketRepo;

    private final UserRepo userRepo;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;


    @Override
    public Ticket createTicket(Ticket ticket) throws JSONException, IOException {
        //send otp
        String ticketId = VerifyCodeUtil.generateVerifyCode(5,"TUVWXYZ123456");
        SendRequestUtil.sendPhoneNumberVerificationCode(ticket.getUser().getPhoneNumber(),ticketId);
        return ticketRepo.save(ticket);
    }

    @Override
    public List<Ticket> getUserTickets(Long userId) {
        User user = userRepo.findById(userId).orElse(null);
        if(user != null){
            return user.getUserTickets();
        }
        return null;
    }

    @Override
    public List<Ticket> getAll() {
        return ticketRepo.findAll();
    }

    @Override
    public void approveTicket(Long ticketId) {
        Ticket ticket = ticketRepo.findById(ticketId).orElse(null);
        if(ticket != null){
            //send email
            String emailBody = "Dear "+ticket.getUser().getFirstName() + ",\n"+
                    "Congratulations, you're ticket is approved. Thank you for booking a ticket with Nyandungu Park. \n\n"+
                    "Nyandungu Management";
            try {
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setFrom(sender);
                mailMessage.setTo(ticket.getUser().getUsername());
                mailMessage.setText(emailBody);
                mailMessage.setSubject("Nyandungu Ticket");
                javaMailSender.send(mailMessage);
                ticket.setStatus("Approved");
                ticketRepo.save(ticket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void rejectTicket(Long ticketId) {
        Ticket ticket = ticketRepo.findById(ticketId).orElse(null);
        if(ticket != null){
            //send email
            String emailBody = "Dear "+ticket.getUser().getFirstName() + ",\n"+
                    "Commiserations , your ticket was declined. No payment was received on our end . \n\n"+
                    "Nyandungu Management";
            try {
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setFrom(sender);
                mailMessage.setTo(ticket.getUser().getUsername());
                mailMessage.setText(emailBody);
                mailMessage.setSubject("Nyandungu Ticket");
                javaMailSender.send(mailMessage);
                ticket.setStatus("Rejected");
                ticketRepo.save(ticket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
