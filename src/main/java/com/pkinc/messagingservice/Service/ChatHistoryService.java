package com.pkinc.messagingservice.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pkinc.messagingservice.Repository.ChatHistoryRepository;
import com.pkinc.messagingservice.Response.PagedResponse;
import com.pkinc.messagingservice.model.ChatHistory;

@Service 
public class ChatHistoryService {

    @Autowired
    ChatHistoryRepository chatHistoryRepository;

    public Boolean checkIfChatHistoryExists(Long senderId , Long receiverId){
        ChatHistory chatHistory = chatHistoryRepository.getUsersChatHistory(senderId, receiverId);
        if (Objects.isNull(chatHistory)) return false;
        return true;
    }


    public ChatHistory newChatHistory(Long senderId , Long receiverId,String message,String date){
        ChatHistory chatHistory = new ChatHistory();
        chatHistory.setSenderId(senderId);
        chatHistory.setReceiverId(receiverId);
        chatHistory.setLastMessage(message);
        chatHistory.setLastMessageDate(date);
        return chatHistoryRepository.save(chatHistory);
    }

    public ChatHistory updateChatHistory(Long senderId , Long receiverId,String message,String date){
        ChatHistory chatHistory = chatHistoryRepository.getUsersChatHistory(senderId, receiverId);
        chatHistory.setChatHistoryId(chatHistory.getChatHistoryId());
        chatHistory.setSenderId(senderId);
        chatHistory.setReceiverId(receiverId);
        chatHistory.setLastMessage(message);
        chatHistory.setLastMessageDate(date);
        return chatHistoryRepository.save(chatHistory);
    }

    public PagedResponse<ChatHistory> getAllUsersChatHistory(Long userId, int page, int size, String sort){
        if (Objects.equals(sort, "DESC")) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "lastMessageDate");
        Page<ChatHistory> chatHistoryPage = chatHistoryRepository.findAllUsersChatHistory( pageable,userId);
        if(chatHistoryPage.getTotalElements()==0){
            return new PagedResponse<>(Collections.emptyList(), chatHistoryPage.getNumber(), chatHistoryPage.getSize(), chatHistoryPage.getTotalElements(), 
            chatHistoryPage.getTotalPages(), chatHistoryPage.isLast());
        }
        List<ChatHistory> chatHistoryList = chatHistoryPage.toList();
        return new PagedResponse<>(chatHistoryList, chatHistoryPage.getNumber(), chatHistoryPage.getSize(), chatHistoryPage.getTotalElements(),
        chatHistoryPage.getTotalPages(), chatHistoryPage.isLast());
        }
        else{
            Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "lastMessageDate");
            Page<ChatHistory> chatHistoryPage = chatHistoryRepository.findAllUsersChatHistory( pageable,userId);
            if(chatHistoryPage.getTotalElements()==0){
                return new PagedResponse<>(Collections.emptyList(), chatHistoryPage.getNumber(), chatHistoryPage.getSize(), chatHistoryPage.getTotalElements(), 
                chatHistoryPage.getTotalPages(), chatHistoryPage.isLast());
            }
            List<ChatHistory> chatHistoryList = chatHistoryPage.toList();
            return new PagedResponse<>(chatHistoryList, chatHistoryPage.getNumber(), chatHistoryPage.getSize(), chatHistoryPage.getTotalElements(),
            chatHistoryPage.getTotalPages(), chatHistoryPage.isLast());
        }


        
    }

    
}
