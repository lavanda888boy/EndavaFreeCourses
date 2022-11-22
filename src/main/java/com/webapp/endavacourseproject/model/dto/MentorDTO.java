package com.webapp.endavacourseproject.model.dto;

import com.webapp.endavacourseproject.model.Mentor;
import lombok.*;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@NoArgsConstructor
@Getter
@Setter
public class MentorDTO {

    @Id
    @NotNull()
    private Long id;

    @NotNull()
    private String firstName;

    @NotNull()
    private String lastName;

    @NotNull()
    private String email;

    @NotNull()
    private boolean workingState;

    public MentorDTO(Mentor mentor){
        this.setId(mentor.getId());
        this.setFirstName(mentor.getFirstName());
        this.setLastName(mentor.getLastName());
        this.setEmail(mentor.getEmail());
        this.setWorkingState(mentor.isWorkingState());
    }
}
