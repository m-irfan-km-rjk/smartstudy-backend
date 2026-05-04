package com.irfan.smartstudy.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalTime;

@Getter
public class StudySessionUpdateRequest {
    private LocalTime startTime;
    private LocalTime endTime;
    private Long subjectId;
}
