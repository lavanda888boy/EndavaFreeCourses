package com.webapp.endavacourseproject.model.dto;

import com.webapp.endavacourseproject.model.Industry;
import com.webapp.endavacourseproject.model.Mentor;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class MentorDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    public MentorDTO(Mentor mentor){
        this.setId(mentor.getId());
        this.setFirstName(mentor.getFirstName());
        this.setLastName(mentor.getLastName());
        this.setEmail(mentor.getEmail());
    }
}
