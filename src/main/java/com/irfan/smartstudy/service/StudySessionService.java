package com.irfan.smartstudy.service;

import com.irfan.smartstudy.dto.StudySessionRequest;
import com.irfan.smartstudy.dto.StudySessionUpdateRequest;
import com.irfan.smartstudy.model.StudySession;
import com.irfan.smartstudy.model.Subject;
import com.irfan.smartstudy.model.User;
import com.irfan.smartstudy.repository.StudySessionRepository;
import com.irfan.smartstudy.repository.SubjectRepository;
import com.irfan.smartstudy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

@Service
public class StudySessionService {
    @Autowired
    private StudySessionRepository studySessionRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SubjectRepository subjectRepository;

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

    public StudySession createStudySession(StudySessionRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found."));
        Subject subject = subjectRepository.findById(request.getSubjectId()).orElseThrow(() -> new RuntimeException("Subject not found."));

        if (request.getEndTime().isBefore(request.getStartTime())) {
            throw new IllegalArgumentException("End time must be after start time");
        }

        StudySession studySession = new StudySession();

        studySession.setDuration(Duration.between(request.getStartTime(),request.getEndTime()));
        studySession.setUser(user);
        studySession.setSubject(subject);
        studySession.setStartTime(request.getStartTime());
        studySession.setEndTime(request.getEndTime());

        return studySessionRepository.save(studySession);
    }

    public StudySession updateStudySessionById(Long id, StudySessionUpdateRequest request) {
        StudySession studySession = studySessionRepository.findById(id).orElseThrow(() -> new RuntimeException("Study Session not found."));

        LocalTime start = request.getStartTime() != null ? request.getStartTime() : studySession.getStartTime();
        LocalTime end = request.getEndTime() != null ? request.getEndTime() : studySession.getEndTime();

        if(end.isBefore(start)) {
            throw new IllegalArgumentException("End time should not be before start time");
        }

        if(request.getSubjectId() != null) {
            Subject subject = subjectRepository.findById(request.getSubjectId()).orElseThrow(() -> new RuntimeException("Subject not found."));
            studySession.setSubject(subject);
        }

        if(request.getStartTime() != null) {
            studySession.setStartTime(request.getStartTime());
        }

        if(request.getEndTime() != null) {
            studySession.setEndTime(request.getEndTime());
        }

        studySession.setDuration(Duration.between(start,end));

        return studySessionRepository.save(studySession);
    }

    public void deleteStudySessionById(Long id) {
        if(!studySessionRepository.existsById(id)) {
            throw new RuntimeException("Study Session not found");
        }

        studySessionRepository.deleteById(id);
    }
}
