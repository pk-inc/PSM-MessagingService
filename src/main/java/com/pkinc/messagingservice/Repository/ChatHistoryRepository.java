package com.pkinc.messagingservice.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pkinc.messagingservice.model.ChatHistory;

public interface ChatHistoryRepository extends JpaRepository<ChatHistory,Long> {

    @Query(value = "select u from ChatHistory u where u.senderId =:senderId or u.senderId =:receiverId and u.receiverId =: receiverId")
    ChatHistory getUsersChatHistory(@Param("senderId")Long senderId, @Param("receiverId") Long receiverId);

     @Query(value = "select u from ChatHistory u where u.senderId =?1 or u.receiverId =?1")
     Page<ChatHistory> findAllUsersChatHistory(Pageable pageable,Long userId);

     
}
