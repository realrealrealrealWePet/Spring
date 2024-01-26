package com.example.SpringVersion.chat.dto;


import com.example.SpringVersion.chat.entity.ChatMessage;
import com.example.SpringVersion.chat.entity.ChatRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatMessageRequest {

    public enum MessageType{
        ENTER, TALK
    }
    private String sender;
    private String message;
    private ChatRoom chatRoom;

    @Builder
    public ChatMessageRequest(String sender, String message, ChatRoom chatRoom) {
        this.sender = sender;
        this.message = message;
        this.chatRoom = chatRoom;
    }

    public ChatMessage toEntity() {
        return ChatMessage.builder()
                .sender(this.sender)
                .message(this.message)
                .chatRoom(this.chatRoom)
                .build();
    }
}