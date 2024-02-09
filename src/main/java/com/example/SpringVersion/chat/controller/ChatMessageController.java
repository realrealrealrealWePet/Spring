package com.example.SpringVersion.chat.controller;

import com.example.SpringVersion.chat.dto.ChatMessageRequest;
import com.example.SpringVersion.user.entity.User;
import com.example.SpringVersion.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatMessageController {

    private final SimpMessageSendingOperations sendingOperations;

    private final UserRepository userRepository;



    @MessageMapping("/message/{chatRoomId}")
    public void enter(ChatMessageRequest message) {
        //Test
        User user = userRepository.getById(1L);

        if (ChatMessageRequest.MessageType.ENTER.equals(message.getType())) {
            message.setMessage(message.getSender()+"님이 입장 하였습니다.");
        }

        sendingOperations.convertAndSend("/topic/chat/room/"+message.getChatRoomId().getId(),message);
    }
}