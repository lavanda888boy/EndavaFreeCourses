package com.webapp.endavacourseproject.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Data
@Table(name = "users",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"firstName", "lastName"})
        })
public class User {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Email
    @Column(nullable = false)
    private String email;

    private String activityDomain;

    @OneToOne
    private Mentor mentor;
}
