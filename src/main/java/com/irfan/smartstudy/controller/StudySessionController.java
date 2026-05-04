package com.irfan.smartstudy.controller;

import com.irfan.smartstudy.dto.StudySessionRequest;
import com.irfan.smartstudy.dto.StudySessionUpdateRequest;
import com.irfan.smartstudy.model.StudySession;
import com.irfan.smartstudy.service.StudySessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studysession")
public class StudySessionController {
    @Autowired
    private StudySessionService studySessionService;

    @GetMapping("/")
    public List<StudySession> getAllStudySessions() {
        return studySessionService.getAllStudySessions();
    }

    @GetMapping("/user/{id}")
    public List<StudySession> getAllStudySessionsByUserId(@PathVariable Long id) {
        return studySessionService.getAllStudySessionByUserId(id);
    }

    @GetMapping("/{id}")
    public StudySession getStudySessionById(@PathVariable Long id) {
        return studySessionService.getStudySessionById(id);
    }

    @PostMapping("/")
    public StudySession createStudySession(@RequestBody StudySessionRequest request) {
        return studySessionService.createStudySession(request);
    }

    @PatchMapping("/{id}")
    public StudySession updateStudySession(@PathVariable Long id, @RequestBody StudySessionUpdateRequest request) {
        return studySessionService.updateStudySessionById(id,request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudySession(@PathVariable Long id) {
        studySessionService.deleteStudySessionById(id);
        return ResponseEntity.noContent().build();
    }
}
