package com.irfan.smartstudy.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.time.LocalDate;

@Entity
@Table(name = "stats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stats {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Duration totalStudyTime;

    @Column(nullable = false)
    @Builder.Default
    private int tasksCompleted = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
}
