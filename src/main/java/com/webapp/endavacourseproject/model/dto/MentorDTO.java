package com.webapp.endavacourseproject.model.dto;

import com.webapp.endavacourseproject.model.Industry;
import lombok.*;

import java.util.List;

@Data
public class MentorDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private List<Industry> industries;
}
