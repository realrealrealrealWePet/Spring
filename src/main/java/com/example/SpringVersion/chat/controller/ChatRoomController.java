package com.example.SpringVersion.chat.controller;



import com.example.SpringVersion.chat.dto.ChatRoomRequest;
import com.example.SpringVersion.chat.dto.ChatRoomResponse;
import com.example.SpringVersion.chat.entity.ChatMessage;
import com.example.SpringVersion.chat.entity.ChatRoom;
import com.example.SpringVersion.chat.repository.ChatMessageRepository;
import com.example.SpringVersion.chat.repository.ChatRoomRepository;
import com.example.SpringVersion.chat.service.ChatService;
import com.example.SpringVersion.global.dto.JsonResponse;
import com.example.SpringVersion.user.entity.User;
import com.example.SpringVersion.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatService chatService;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;

    // 해당 유저의 모든 채팅방 목록 반환 (채팅 페이지 메인 화면)
    @GetMapping("/chat/rooms")
    @ResponseBody
    public ResponseEntity<JsonResponse> getRoomsList() {

        // User info
        User user = userRepository.getById(1L);

        // Chat Room Info
        List<ChatRoom> chatRooms = chatRoomRepository.findAllByUserId(1L);


        List<ChatRoomResponse.getChatRoomList> getChatRoomLists = chatService.getChatRoomLists(chatRooms, user);

        return ResponseEntity.ok(new JsonResponse(true, 200,
                "getRoomsList", getChatRoomLists));
    }

    // 첫 메세지 전송 (채팅방 개설)
    @PostMapping("/chat/room")
    public ResponseEntity<JsonResponse> createRoom(@RequestParam String nickname) {
        User user = userRepository.getById(1L);

        ChatRoomRequest req = ChatRoomRequest.builder().Sender(user.getNickname()).
                Receiver(nickname).build();

        return ResponseEntity.ok(new JsonResponse(true, 200,
                "enterRoom", new ChatRoomResponse.getRoomMessageList()));
    }

    // 이미 개설된 채팅방 입장
    @GetMapping("chat/room/{roomId}")
    public ResponseEntity<JsonResponse> enterRoom(@PathVariable String roomId) {


        return ResponseEntity.ok(new JsonResponse(true, 200,
                "enterRoom", new ChatRoomResponse.getRoomMessageList()));
    }



}