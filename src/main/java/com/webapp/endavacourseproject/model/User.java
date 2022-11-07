package com.webapp.endavacourseproject.model;

import com.webapp.endavacourseproject.model.dto.UserDTO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"firstName", "lastName"})
        })
public class User {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column()
    @NotNull(message = "First name is required")
    private String firstName;

    @Column()
    @NotNull(message = "Last name is required")
    private String lastName;

    @Email
    @Column()
    @NotNull(message = "Email is required")
    private String email;

    private String activityDomain;

    @OneToOne
    private Mentor mentor;

    public User(UserDTO userDTO){
        this.setId(userDTO.getId());
        this.setFirstName(userDTO.getFirstName());
        this.setLastName(userDTO.getLastName());
        this.setEmail(userDTO.getEmail());
        this.setActivityDomain(userDTO.getActivityDomain());
    }
}
