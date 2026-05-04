package com.irfan.smartstudy.dto;

import lombok.Getter;

import java.time.Duration;

@Getter
public class StatsUpdateRequest {
    private Duration totalStudyTime;
    private Integer tasksCompleted;
}
