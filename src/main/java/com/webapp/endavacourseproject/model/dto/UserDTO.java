package com.webapp.endavacourseproject.model.dto;

import com.webapp.endavacourseproject.model.User;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String activityDomain;

    private MentorDTO mentor;

    public UserDTO(User user){
        this.setId(user.getId());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setEmail(user.getEmail());
        this.setActivityDomain(user.getActivityDomain());
    }
}
