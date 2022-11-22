package com.webapp.endavacourseproject.model;

import com.webapp.endavacourseproject.model.dto.MentorDTO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "mentors", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"firstName", "lastName"})
})
public class Mentor {

    @Id
    @Column(name = "id", nullable = false)
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
    @Column(nullable = false)
    @NotNull()
    private String email;

    @Column(nullable = false)
    @NotNull()
    private boolean workingState;

    @ManyToMany
    private List<Industry> industries;

    public Mentor(MentorDTO mentorDTO){
        this.setId(mentorDTO.getId());
        this.setFirstName(mentorDTO.getFirstName());
        this.setLastName(mentorDTO.getLastName());
        this.setEmail(mentorDTO.getEmail());
        this.setWorkingState(mentorDTO.isWorkingState());
    }

    public Mentor(Long id, String firstName, String lastName, String email, boolean workingState){
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setWorkingState(workingState);
    }
}
