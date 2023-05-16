package com.ticketing_system.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userType = "NON_ADMIN";

    private String firstName;

    private String lastName;

    private String username;

    private String address;

    private String phoneNumber;

    private String password;

    private Integer age;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<Ticket> userTickets = new ArrayList<>();

    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate createdAt;

    @Column(nullable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate updatedAt;

}
