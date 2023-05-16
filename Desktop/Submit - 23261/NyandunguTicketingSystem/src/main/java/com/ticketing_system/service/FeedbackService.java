package com.ticketing_system.service;

import com.ticketing_system.model.Feedback;

import java.util.List;

public interface FeedbackService {

    Feedback createFeedback(Feedback feedback);

    List<Feedback> getAll();
}
