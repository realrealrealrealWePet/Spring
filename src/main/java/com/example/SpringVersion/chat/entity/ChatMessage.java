package com.example.SpringVersion.chat.entity;

import com.example.SpringVersion.global.timestamp.Timestamped;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sender;

    private String message;

    @ManyToOne
    private ChatRoom chatRoom;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public ChatMessage(String sender, String message, ChatRoom chatRoom) {
        this.sender = sender;
        this.message = message;
        this.chatRoom = chatRoom;
    }
}