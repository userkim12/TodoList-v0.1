package com.todo.service;

import com.todo.exception.BusinessLogicException;
import com.todo.exception.ExceptionCode;
import com.todo.repository.TaskRepository;
import org.springframework.stereotype.Service;
import com.todo.entity.Task;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        //검증 구현

        return taskRepository.save(task);
    }

    public Task updateTask(Task task) {
        Task findTask = findVerifiedTask(task.getTaskId());

        Optional.ofNullable(task.getTitle()).ifPresent(findTask::setTitle);
        findTask.setOrder(task.getOrder());
        findTask.setCompleted(task.isCompleted());

        return taskRepository.save(findTask);
    }

    public Task findTask(long id) {
        return findVerifiedTask(id);
    }

    public List<Task> findTasks() {
        List<Task> taskList = taskRepository.findAll();
        if(taskList.isEmpty()) throw new BusinessLogicException(ExceptionCode.TASKS_NOT_FOUND);
        return taskList;
    }

    public void deleteTask(long id) throws Exception {
        Task findTask = findVerifiedTask(id);

        taskRepository.delete(findTask);
    }

    public void deleteTasks() {
        taskRepository.deleteAll();
    }

    private Task findVerifiedTask(long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        Task findTask = optionalTask.orElseThrow(() -> new BusinessLogicException(ExceptionCode.TASK_NOT_FOUND));
        return  findTask;
    }
}
