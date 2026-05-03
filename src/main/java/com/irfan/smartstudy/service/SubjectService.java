package com.irfan.smartstudy.service;

import com.irfan.smartstudy.dto.SubjectRequest;
import com.irfan.smartstudy.dto.SubjectUpdateRequest;
import com.irfan.smartstudy.model.Subject;
import com.irfan.smartstudy.model.User;
import com.irfan.smartstudy.repository.SubjectRepository;
import com.irfan.smartstudy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id).orElseThrow(() -> new RuntimeException("Subject not found."));
    }

    public Subject createSubject(SubjectRequest request) {
        Subject subject = new Subject();
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found."));

        subject.setName(request.getName());
        subject.setUser(user);
        if(request.getColor() != null) {
            subject.setColor(request.getColor());
        }
        if(request.getDescription() != null) {
            subject.setDescription(request.getDescription());
        }

        return subjectRepository.save(subject);
    }

    public Subject updateSubjectById(Long id, SubjectUpdateRequest request) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new RuntimeException("No such subject."));

        if(request.getName() != null) {
            subject.setName(request.getName());
        }

        if(request.getDescription() != null) {
            subject.setDescription(request.getDescription());
        }

        if(request.getColor() != null) {
            subject.setColor(request.getColor());
        }

        return subjectRepository.save(subject);
    }

    public void deleteSubjectById(Long id) {
        if(!subjectRepository.existsById(id)) {
            throw new RuntimeException("No such subject.");
        }

        subjectRepository.deleteById(id);
    }
}
