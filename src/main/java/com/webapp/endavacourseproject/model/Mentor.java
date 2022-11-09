package com.webapp.endavacourseproject.model;

import com.webapp.endavacourseproject.model.dto.MentorDTO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Email(message = "Email is not valid",
            regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE)
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private boolean workingState;

    @OneToMany
    private List<Industry> industries;

    public void Mentor(MentorDTO mentorDTO){
        this.setId(mentorDTO.getId());
        this.setFirstName(mentorDTO.getFirstName());
        this.setLastName(mentorDTO.getLastName());
        this.setEmail(mentorDTO.getEmail());
    }
}
