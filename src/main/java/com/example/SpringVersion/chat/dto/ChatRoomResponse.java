package com.example.SpringVersion.chat.dto;

import com.example.SpringVersion.chat.entity.ChatRoom;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class ChatRoomResponse {
    private Long id;
    private String roomName;
    private String createdDate;
    private String updatedDate;

    public ChatRoomResponse(ChatRoom entity, String user) {
        this.id = entity.getId();
        this.roomName = user; //채팅방 이름은 상대방의 닉네임
        this.createdDate = entity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
        this.updatedDate = entity.getModifiedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
    }
}
