package com.webapp.endavacourseproject.model.dto;

import com.webapp.endavacourseproject.model.User;
import lombok.*;

import javax.persistence.Id;
import javax.validation.constraints.*;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    @Id
    @NotNull()
    private Long id;

    @NotNull()
    private String firstName;

    @NotNull()
    private String lastName;

    @Email(message = "Email is not valid",
            regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE)
    @NotNull()
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
