package com.example.SpringVersion.chat.controller;


import com.example.SpringVersion.chat.dto.test.ChatRoomDTO;
import com.example.SpringVersion.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService service;

    @PostMapping  // 첫 메세지를 보낼 때 채팅방 생성하게 됨
    public ChatRoomDTO createRoom(@RequestParam String name){
        return service.createRoom(name);
    }

    @GetMapping
    public List<ChatRoomDTO> findAllRooms(){
        return service.findAllRoom();
    }


}