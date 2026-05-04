package com.irfan.smartstudy.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDate;

@Getter
public class StatsRequest {
    private LocalDate date;
    private Duration totalStudyTime;
    private Integer tasksCompleted;
    @NotNull
    private Long userId;
}
