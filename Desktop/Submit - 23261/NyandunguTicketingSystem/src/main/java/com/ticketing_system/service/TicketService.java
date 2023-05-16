package com.ticketing_system.service;

import com.ticketing_system.model.Ticket;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public interface TicketService {

    Ticket createTicket(Ticket ticket) throws JSONException, IOException;

    List<Ticket> getUserTickets(Long userId);

    List<Ticket> getAll();

    void approveTicket(Long ticketId);

    void rejectTicket(Long ticketId);

}
