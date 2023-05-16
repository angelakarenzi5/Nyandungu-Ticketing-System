package com.ticketing_system.repo;

import com.ticketing_system.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepo extends JpaRepository<Feedback,Long> {
}
