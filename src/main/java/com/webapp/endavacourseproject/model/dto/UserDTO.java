package com.webapp.endavacourseproject.model.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@RequiredArgsConstructor
public class UserDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String activityDomain;

    private MentorDTO mentor;
}
