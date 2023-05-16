package com.ticketing_system.repo;

import com.ticketing_system.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepo extends JpaRepository<Ticket,Long> {
}
