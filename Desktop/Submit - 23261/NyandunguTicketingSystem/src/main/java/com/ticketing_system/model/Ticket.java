package com.ticketing_system.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    @ManyToOne
    private User user;

    private String status = "Pending";

    private Integer number;

    private String activity;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
}
