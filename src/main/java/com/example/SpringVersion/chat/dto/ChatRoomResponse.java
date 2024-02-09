package com.example.SpringVersion.chat.dto;

import com.example.SpringVersion.chat.entity.ChatMessage;
import com.example.SpringVersion.chat.entity.ChatRoom;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.List;


public class ChatRoomResponse {
    @NoArgsConstructor
    @Data
    public static class getChatRoomList {
        private Long roomId;
        private String lastMessage;
        private String updatedDate;
        private String userName;

        @Builder
        public getChatRoomList(ChatRoom room, ChatMessage message, String userName) {
            this.roomId = room.getId();
            this.lastMessage = message.getMessage(); //채팅방 이름은 상대방의 닉네임
            this.updatedDate = message.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
            this.userName = userName;
        }
    }

    @NoArgsConstructor
    @Data
    public static class getRoomMessageList {
        private Long roomId;
        private String Message;


    }

}