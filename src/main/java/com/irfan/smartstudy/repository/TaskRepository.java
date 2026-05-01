package com.irfan.smartstudy.repository;

import com.irfan.smartstudy.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}