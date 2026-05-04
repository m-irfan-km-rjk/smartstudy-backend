package com.irfan.smartstudy.controller;

import com.irfan.smartstudy.dto.StatsRequest;
import com.irfan.smartstudy.dto.StatsUpdateRequest;
import com.irfan.smartstudy.model.Stats;
import com.irfan.smartstudy.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stats")
public class StatsController {
    @Autowired
    private StatsService statsService;

    @GetMapping("/")
    public List<Stats> getAllStats() {
        return statsService.getAllStats();
    }

    @GetMapping("/user/{id}")
    public List<Stats> getAllStatsByUserId(@PathVariable Long id) {
        return statsService.getAllStatsByUserId(id);
    }

    @GetMapping("/{id}")
    public Stats getStatsById(@PathVariable Long id) {
        return statsService.getStatsById(id);
    }

    @PostMapping("/")
    public Stats createStats(@RequestBody StatsRequest request) {
        return statsService.createStats(request);
    }

    @PatchMapping("/{id}")
    public Stats updateStats(@PathVariable Long id, @RequestBody StatsUpdateRequest request) {
        return statsService.updateStatsById(id,request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStats(@PathVariable Long id) {
        statsService.deleteStatsById(id);
        return ResponseEntity.noContent().build();
    }
}
