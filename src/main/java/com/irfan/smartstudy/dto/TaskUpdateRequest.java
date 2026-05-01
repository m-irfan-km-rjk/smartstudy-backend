package com.irfan.smartstudy.dto;

import com.irfan.smartstudy.model.Status;

import java.time.LocalDateTime;

public class TaskUpdateRequest {
    public String title;
    public String details;
    public LocalDateTime dueDate;
    public int priority;
    public String status;
}
