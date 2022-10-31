package com.webapp.endavacourseproject.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String activityDomain;

    @OneToOne
    private Mentor mentor;
}
