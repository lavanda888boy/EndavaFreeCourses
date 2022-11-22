package com.webapp.endavacourseproject.model;

import com.webapp.endavacourseproject.model.dto.UserDTO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
    @NotNull()
    private Long id;

    @Column(nullable = false)
    @NotNull()
    private String firstName;

    @Column(nullable = false)
    @NotNull()
    private String lastName;

    @Email(message = "Email is not valid",
            regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE)
    @Column()
    @NotNull()
    private String email;

    @NotNull()
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

    public User(Long id, String firstName, String lastName, String email, String activityDomain){
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setActivityDomain(activityDomain);
    }
}
