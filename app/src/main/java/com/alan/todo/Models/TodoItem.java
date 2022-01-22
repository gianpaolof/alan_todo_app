package com.alan.todo.Models;

public class TodoItem {
    private String id;
    private String title;
    private String description;
    private long timeStamp;
    private boolean complete;

    public TodoItem() {
    }

    public TodoItem(String title, String description, long timeStamp, boolean complete) {
        this.title = title;
        this.description = description;
        this.timeStamp = timeStamp;
        this.complete = complete;
    }

    public TodoItem(String id, String title, String description, long timeStamp, boolean complete) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.timeStamp = timeStamp;
        this.complete = complete;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
