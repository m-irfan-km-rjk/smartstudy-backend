package com.irfan.smartstudy.service;

import com.irfan.smartstudy.dto.StatsRequest;
import com.irfan.smartstudy.dto.StatsUpdateRequest;
import com.irfan.smartstudy.model.Stats;
import com.irfan.smartstudy.model.User;
import com.irfan.smartstudy.repository.StatsRepository;
import com.irfan.smartstudy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatsService {
    @Autowired
    private StatsRepository statsRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Stats> getAllStats() {
        return statsRepository.findAll();
    }

    public List<Stats> getAllStatsByUserId(Long id) {
        if(!userRepository.existsById(id)) {
            throw new RuntimeException("User not Found");
        }

        return statsRepository.findByUserId(id);
    }

    public Stats getStatsById(Long id) {
        return statsRepository.findById(id).orElseThrow(() -> new RuntimeException("Stat not found"));
    }

    public Stats createStats(StatsRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found."));

        Stats stats = new Stats();

        stats.setDate(request.getDate());
        stats.setUser(user);
        stats.setTotalStudyTime(request.getTotalStudyTime());
        if(request.getTasksCompleted() != null) {
            stats.setTasksCompleted(request.getTasksCompleted());
        }

        return statsRepository.save(stats);
    }

    public Stats updateStatsById(Long id, StatsUpdateRequest request) {
        Stats stats = statsRepository.findById(id).orElseThrow(() -> new RuntimeException("Study Session not found."));

        if(request.getTasksCompleted() != null) {
            stats.setTasksCompleted(request.getTasksCompleted());
        }

        if(request.getTotalStudyTime() != null) {
            stats.setTotalStudyTime(request.getTotalStudyTime());
        }

        return statsRepository.save(stats);
    }

    public void deleteStatsById(Long id) {
        if(!statsRepository.existsById(id)) {
            throw new RuntimeException("Study Session not found");
        }

        statsRepository.deleteById(id);
    }
}
