package com.irfan.smartstudy.service;

import com.irfan.smartstudy.model.StudySession;
import com.irfan.smartstudy.repository.StudySessionRepository;
import com.irfan.smartstudy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudySessionService {
    @Autowired
    private StudySessionRepository studySessionRepository;

    @Autowired
    private UserRepository userRepository;

    public List<StudySession> getAllStudySessions() {
        return studySessionRepository.findAll();
    }

    public StudySession getStudySessionById(Long id) {
        return studySessionRepository.findById(id).orElseThrow(() -> new RuntimeException("Study Session not found."));
    }

    public List<StudySession> getAllStudySessionByUserId(Long id) {
        if(!userRepository.existsById(id)) {
            throw new RuntimeException("User not found.");
        }
        return studySessionRepository.findByUserId(id);
    }

    public StudySession createStudySession() {

    }
}
