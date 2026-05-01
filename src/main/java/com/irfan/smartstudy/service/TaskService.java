package com.irfan.smartstudy.service;

import com.irfan.smartstudy.dto.TaskRequest;
import com.irfan.smartstudy.model.Subject;
import com.irfan.smartstudy.model.Task;
import com.irfan.smartstudy.model.User;
import com.irfan.smartstudy.repository.SubjectRepository;
import com.irfan.smartstudy.repository.TaskRepository;
import com.irfan.smartstudy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found."));
    }

    public Task createTask(TaskRequest req) {
        Subject subject = subjectRepository.findById(req.subjectId).orElseThrow(() -> new RuntimeException("Subject doesn't exist."));
        User user = userRepository.findById(req.userId).orElseThrow(() -> new RuntimeException("User doesn't exist."));

        Task task = new Task();
        task.setTitle(req.title);
        task.setDetails(req.details);
        task.setDueDate(req.dueDate);
        task.setPriority(req.priority);
        task.setSubject(subject);
        task.setUser(user);

        return taskRepository.save(task);
    }

    public Task updateTaskById(Long id, Task task) {
        Task existing = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));

        if(task.getTitle() != null) {
            existing.setTitle(task.getTitle());
        }

        if(task.getStatus() != null) {
            existing.setStatus(task.getStatus());
        }

        if(task.getDetails() != null) {
            existing.setDetails(task.getDetails());
        }

        if(task.getDueDate() != null) {
            existing.setDueDate(task.getDueDate());
        }

        return taskRepository.save(existing);
    }
}
