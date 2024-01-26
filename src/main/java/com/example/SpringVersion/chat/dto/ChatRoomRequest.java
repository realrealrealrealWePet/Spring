package com.example.SpringVersion.chat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatRoomRequest {


    private String Sender;
    private String Receiver;

    @Builder
    public ChatRoomRequest(String Sender, String Receiver) {
        this.Sender = Sender;
        this.Receiver = Receiver;
    }

}
