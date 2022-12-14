package com.todo.mapper;

import com.todo.dto.TaskDto;
import com.todo.entity.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task TaskPostDtoToTask(TaskDto.Post post);
    Task TaskPatchDtoToTask(TaskDto.Patch patch);
    TaskDto.Response TaskToTaskResponseDto(Task task);
    List<TaskDto.Response> TasksToTaskResponseDtos(List<Task> task);
}
