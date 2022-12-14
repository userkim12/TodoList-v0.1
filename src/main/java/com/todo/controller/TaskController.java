package com.todo.controller;

import com.todo.entity.Task;
import com.todo.dto.TaskDto;
import com.todo.mapper.TaskMapper;
import com.todo.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/v1/lists")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper mapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.mapper = taskMapper;
    }

    @PostMapping
    public ResponseEntity postTask(@RequestBody TaskDto.Post requestBody) {
        Task task = mapper.TaskPostDtoToTask(requestBody);

        Task createdTask = taskService.createTask(task);
        return new ResponseEntity<>(mapper.TaskToTaskResponseDto(createdTask), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity getTask(@PathVariable("id") long id) {
        Task task = taskService.findTask(id);

        return new ResponseEntity<>(mapper.TaskToTaskResponseDto(task), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getTasks() {
        List<Task> response = taskService.findTasks();
        return new ResponseEntity<>(mapper.TasksToTaskResponseDtos(response), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchTask(@PathVariable long id, @RequestBody TaskDto.Patch requestBody){
        requestBody.setId(id);

        Task task = taskService.updateTask(mapper.TaskPatchDtoToTask(requestBody));

        return new ResponseEntity<>(mapper.TaskToTaskResponseDto(task), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable long id) throws Exception {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity deleteTasks() {
        taskService.deleteTasks();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
