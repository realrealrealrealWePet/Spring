package com.example.SpringVersion.chat.entity;



import com.example.SpringVersion.global.timestamp.Timestamped;
import com.example.SpringVersion.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class ChatRoom extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User sender;   // 처음 보낸 사람 닉네임 저장

    @ManyToOne
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User receiver;   // 처음 받은 사람 닉네임 저장

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.REMOVE)
    private List<ChatMessage> chatMessageList;  // 해당 채팅방에서 기록된 모든 채팅


    @Builder
    public ChatRoom(User Sender, User Receiver) {
        this.sender = Sender;
        this.receiver = Receiver;
    }


}