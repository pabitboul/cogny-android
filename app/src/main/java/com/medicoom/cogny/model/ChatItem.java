package com.medicoom.cogny.model;

public class ChatItem {
    private long id;
    private boolean isAssistant;
    private int icon;
    private String message;
    private Long userId;
    private String dateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isAssistant() {
        return isAssistant;
    }

    public void setAssistant(boolean assistant) {
        isAssistant = assistant;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
