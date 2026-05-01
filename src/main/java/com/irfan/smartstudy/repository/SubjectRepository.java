package com.irfan.smartstudy.repository;

import com.irfan.smartstudy.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
