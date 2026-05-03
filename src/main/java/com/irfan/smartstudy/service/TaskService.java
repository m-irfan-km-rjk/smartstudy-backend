package com.irfan.smartstudy.service;

import com.irfan.smartstudy.dto.TaskRequest;
import com.irfan.smartstudy.dto.TaskUpdateRequest;
import com.irfan.smartstudy.model.Status;
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

    public List<Task> getTaskByUserId(Long id) {
        if(!userRepository.existsById(id)) {
            throw new RuntimeException("User not found.");
        }

        return taskRepository.findByUserId(id);
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

    public Task updateTaskById(Long id, TaskUpdateRequest req) {
        Task existing = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (req.title != null) existing.setTitle(req.title);
        if (req.details != null) existing.setDetails(req.details);
        if (req.dueDate != null) existing.setDueDate(req.dueDate);
        if (req.priority != null) existing.setPriority(req.priority);
        if (req.status != null) existing.setStatus(Status.fromString(req.status));

        return taskRepository.save(existing);
    }

    public void deleteTaskById(Long id) {
        if(!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not Found");
        }

        taskRepository.deleteById(id);
    }
}
