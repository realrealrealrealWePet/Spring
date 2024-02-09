package com.example.SpringVersion.chat.repository;


import com.example.SpringVersion.chat.entity.ChatMessage;
import com.example.SpringVersion.chat.entity.ChatRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, java.lang.Long> {
    /** ChatMessage 목록조회 - 기본정렬순, ChatRoom 검색 */
    List<ChatMessage> findAllByChatRoom(ChatRoom chatRoom);

    /** ChatMessage 목록조회 - 조건정렬순, ChatRoom 검색 */
    List<ChatMessage> findAllByChatRoom(ChatRoom chatRoom, Sort sort);

    /** ChatMessage 검색조회 - 기본정렬순, Message 검색 */
    List<ChatMessage> findAllByMessageContaining(String message);

    /** ChatMessage 검색조회 - 조건정렬순, Message 검색 */
    List<ChatMessage> findAllByMessageContaining(String message, Sort sort);

    @Query("SELECT cm FROM ChatMessage cm WHERE cm.chatRoom.id = :chatRoomId ORDER BY cm.createdAt DESC")
    Page<ChatMessage> findLastMessageByChatRoomId(@Param("chatRoomId") Long chatRoomId, Pageable pageable);
}
