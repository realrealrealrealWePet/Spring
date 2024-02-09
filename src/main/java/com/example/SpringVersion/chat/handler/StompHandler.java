package com.example.SpringVersion.chat.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;



@Slf4j
@RequiredArgsConstructor
@Component
public class StompHandler implements ChannelInterceptor {
    // 추후 JWT 인증 처리 관련 로직 추가
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        log.info("Stomp Hanler 실행");
        return message;
    }

}
