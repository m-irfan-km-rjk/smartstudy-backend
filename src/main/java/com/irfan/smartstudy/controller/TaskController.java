package com.irfan.smartstudy.controller;

import com.irfan.smartstudy.dto.TaskRequest;
import com.irfan.smartstudy.dto.TaskUpdateRequest;
import com.irfan.smartstudy.model.Task;
import com.irfan.smartstudy.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> getTasksByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(taskService.getTaskByUserId(userId));
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping("/")
    public Task createTask(@RequestBody TaskRequest req) {
        return taskService.createTask(req);
    }

    @PatchMapping("/{id}")
    public Task updateTaskById(@PathVariable Long id, @RequestBody TaskUpdateRequest req) {
        return taskService.updateTaskById(id,req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }
}
