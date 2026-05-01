package com.irfan.smartstudy.dto;

import java.time.LocalDateTime;

public class TaskRequest {
    public String title;
    public String details;
    public LocalDateTime dueDate;
    public int priority;
    public Long subjectId;
    public Long userId;
}
