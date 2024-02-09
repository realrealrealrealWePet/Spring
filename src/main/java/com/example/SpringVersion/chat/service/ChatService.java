package com.example.SpringVersion.chat.service;



import com.example.SpringVersion.chat.dto.ChatMessageRequest;
import com.example.SpringVersion.chat.dto.ChatRoomRequest;
import com.example.SpringVersion.chat.dto.ChatRoomResponse;
import com.example.SpringVersion.chat.entity.ChatMessage;
import com.example.SpringVersion.chat.entity.ChatRoom;
import com.example.SpringVersion.chat.repository.ChatMessageRepository;
import com.example.SpringVersion.chat.repository.ChatRoomRepository;
import com.example.SpringVersion.user.entity.User;
import com.example.SpringVersion.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Slf4j
@Data
@Service
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;

    private final ChatMessageRepository chatMessageRepository;

    private final UserRepository userRepository;


    @Transactional
    public ChatRoom createRoom(User user, ChatRoomRequest req){
        User receiver = userRepository.findByNickname(req.getReceiver()).get();

        ChatRoom room = ChatRoom.builder().sender(user).receiver(receiver).build();
        chatRoomRepository.save(room);

        return room;
    }


    @Transactional
    public List<ChatRoomResponse.getChatRoomList> getChatRoomLists(List<ChatRoom> rooms, User user){
        List<ChatRoomResponse.getChatRoomList> getChatRoomLists = new ArrayList<>();


        for (ChatRoom room : rooms) {
            ChatMessage lastMessage = chatMessageRepository.findLastMessageByChatRoomId(room.getId(), PageRequest.of(0, 1))
                    .getContent().get(0);

            User otherUser = room.getSender().getId().equals(user.getId()) ? room.getReceiver() : room.getSender();

            ChatRoomResponse.getChatRoomList dto = new ChatRoomResponse.getChatRoomList();
            dto.setRoomId(room.getId());
            dto.setLastMessage(lastMessage.getMessage());
            dto.setUpdatedDate(lastMessage.getCreatedAt().toString());
            dto.setUserName(otherUser.getNickname());

            getChatRoomLists.add(dto);
        }

        return getChatRoomLists;
    }


}
