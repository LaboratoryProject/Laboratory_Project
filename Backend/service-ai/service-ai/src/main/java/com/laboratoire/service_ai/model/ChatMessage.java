package com.laboratoire.service_ai.model;

import java.time.LocalDateTime;

public class ChatMessage {
    private String type; // "User" or "AI"
    private String content;

    // Constructor, getters, and setters
    public ChatMessage(String type, String content) {
        this.type = type;
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}