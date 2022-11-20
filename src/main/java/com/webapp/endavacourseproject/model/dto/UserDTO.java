package com.webapp.endavacourseproject.model.dto;

import com.webapp.endavacourseproject.model.User;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NotNull(message = "ID cannot be null")
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

    public UserDTO(User user){
        this.setId(user.getId());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setEmail(user.getEmail());
        this.setActivityDomain(user.getActivityDomain());
    }
}
