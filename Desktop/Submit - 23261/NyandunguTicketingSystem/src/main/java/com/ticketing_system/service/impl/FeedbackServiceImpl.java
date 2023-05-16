package com.ticketing_system.service.impl;

import com.ticketing_system.model.Feedback;
import com.ticketing_system.repo.FeedbackRepo;
import com.ticketing_system.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepo feedbackRepo;

    @Override
    public Feedback createFeedback(Feedback feedback) {
        return feedbackRepo.save(feedback);
    }

    @Override
    public List<Feedback> getAll() {
        return feedbackRepo.findAll();
    }
}
