package com.webapp.endavacourseproject.model.dto;

import lombok.*;

import java.util.List;

@Data
public class MentorDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private List<IndustryDTO> industries;
}