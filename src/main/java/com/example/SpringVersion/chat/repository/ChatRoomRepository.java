package com.example.SpringVersion.chat.repository;


import com.example.SpringVersion.chat.entity.ChatRoom;
import com.example.SpringVersion.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, java.lang.Long> {


    /** 해당 유저가 속한 모든 채팅방 목록 반환 */
    @Query("SELECT cr FROM ChatRoom cr WHERE cr.sender.id = :userId OR cr.receiver.id = :userId")
    List<ChatRoom> findAllByUserId(@Param("userId") Long userId);

    Boolean existsChatRoomById(Long roomId);
    /** ChatRoom 조회 - Sender, Receiver, 정확히 일치 */
    ChatRoom findBySenderAndReceiver(User Sender, User Receiver);

    /** ChatRoom 목록조회 - 기본정렬순, RoomName 검색, 정확히 일치 */
    List<ChatRoom> findAllBySenderAndReceiver(User Sender, User Receiver);

    /** ChatRoom 목록조회 - 조건정렬순, RoomName 검색, 정확히 일치 */
    //List<ChatRoom> findAllByRoomName(String roomName, Sort sort);

    /** ChatRoom 목록조회 - 기본정렬순, RoomName 검색, 포함 일치 */
    //List<ChatRoom> findAllByRoomNameContaining(String roomName);

    /** ChatRoom 목록조회 - 조건정렬순, RoomName 검색, 포함 일치 */
    //List<ChatRoom> findAllByRoomNameContaining(String roomName, Sort sort);
}