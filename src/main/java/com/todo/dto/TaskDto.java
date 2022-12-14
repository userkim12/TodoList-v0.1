package com.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class TaskDto {
    @Getter
    public static class Post {
        private String title;
        private int order;
        private boolean completed;

    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private long taskId;
        private String title;
        private int order;
        private boolean completed;

        public void setId(long taskId) {
            this.taskId = taskId;
        }
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private long taskId;
        private String title;
        private int order;
        private boolean completed;

    }
}


