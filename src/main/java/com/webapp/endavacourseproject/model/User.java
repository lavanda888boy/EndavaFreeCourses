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
    private Long id;

    @Column()
    @NotNull(message = "First name is required")
    private String firstName;

    @Column()
    @NotNull(message = "Last name is required")
    private String lastName;

    @Email(message = "Email is not valid",
            regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE)
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
