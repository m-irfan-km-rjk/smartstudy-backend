package com.irfan.smartstudy.controller;

import com.irfan.smartstudy.dto.SubjectRequest;
import com.irfan.smartstudy.dto.SubjectUpdateRequest;
import com.irfan.smartstudy.model.Subject;
import com.irfan.smartstudy.service.SubjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @GetMapping("/")
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @GetMapping("/{id}")
    public Subject getSubjectById(@PathVariable Long id) {
        return subjectService.getSubjectById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Subject> createSubject(@Valid @RequestBody SubjectRequest request) {
        return ResponseEntity.ok(subjectService.createSubject(request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Subject> updateSubjectById(@PathVariable Long id, @RequestBody SubjectUpdateRequest request) {
        return ResponseEntity.ok(subjectService.updateSubjectById(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubjectById(@PathVariable Long id) {
        subjectService.deleteSubjectById(id);
        return ResponseEntity.noContent().build();
    }
}
