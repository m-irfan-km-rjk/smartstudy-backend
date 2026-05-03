package com.irfan.smartstudy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SubjectRequest {
    @NotBlank
    private String name;
    private String description;
    private String color = "#FFFFFF";
    @NotNull
    private Long userId;
}
