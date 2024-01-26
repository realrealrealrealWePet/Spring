package com.example.SpringVersion.chat.dto;


import com.example.SpringVersion.chat.entity.ChatMessage;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class ChatMessageResponse {
    private Long id;
    private String sender;
    private String message;
    private String createdDate;
    private String updatedDate;

    public ChatMessageResponse(ChatMessage entity) {
        this.id = entity.getId();
        this.sender = entity.getSender();
        this.message = entity.getMessage();
        this.createdDate = entity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
        this.updatedDate = entity.getModifiedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
    }
}