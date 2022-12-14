package com.todo.exception;

import lombok.Getter;

public enum ExceptionCode {
    TASK_NOT_FOUND(404, "Task Not Found"),

    TASKS_NOT_FOUND(404,"Tasks Not Found");



    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.message = message;
        this.status = status;
    }
}
