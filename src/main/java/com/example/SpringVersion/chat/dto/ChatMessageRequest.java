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

    private ChatMessageRequest.MessageType type; // 메시지 타입
    private String sender;
    private String message;
    private ChatRoom chatRoomId;


    @Builder
    public ChatMessageRequest(MessageType type, String sender, String message, ChatRoom chatRoom) {
        this.type = type;
        this.sender = sender;
        this.message = message;
        this.chatRoomId = chatRoom;
    }

    public ChatMessage toEntity() {
        return ChatMessage.builder()
                .sender(this.sender)
                .message(this.message)
                .chatRoom(this.chatRoomId)
                .build();
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ChatRoom getChatRoomId() {
        return chatRoomId;
    }
}