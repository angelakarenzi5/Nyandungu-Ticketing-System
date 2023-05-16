package com.ticketing_system.controller;

import com.ticketing_system.model.Feedback;
import com.ticketing_system.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;


    @PostMapping(value = "/create")
    public String createFeedback(@ModelAttribute("feedback") Feedback feedback){
        feedbackService.createFeedback(feedback);
        return "home";
    }
}
