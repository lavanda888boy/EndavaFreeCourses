package com.webapp.endavacourseproject.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "industries")
public class Industry {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true, nullable = false)
    private String industryName;
}
