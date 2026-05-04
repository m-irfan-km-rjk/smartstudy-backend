package com.irfan.smartstudy.repository;

import com.irfan.smartstudy.model.Stats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatsRepository extends JpaRepository<Stats,Long> {
    List<Stats> findByUserId(Long userId);
}
