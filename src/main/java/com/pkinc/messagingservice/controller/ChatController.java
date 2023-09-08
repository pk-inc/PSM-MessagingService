package com.pkinc.messagingservice.controller;



import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.pkinc.messagingservice.Response.PagedResponse;
import com.pkinc.messagingservice.Service.ChatHistoryService;
import com.pkinc.messagingservice.config.AppConstants;
import com.pkinc.messagingservice.model.Message;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    ChatHistoryService chatHistoryService;


    @GetMapping("/chatHistory/{userId}")
    public PagedResponse<?> getAllUsersChatHistory(@PathVariable Long userId, @RequestParam(name = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
    @RequestParam(name = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
    @RequestParam(name = "sort" ,defaultValue = "DESC") String sort){
        return chatHistoryService.getAllUsersChatHistory(userId, page, size, sort);
    }

    // @Autowired
    // private RabbitTemplate rabbitTemplate;

    @MessageMapping("/private-message")
    public Message recMessage(@Payload Message message){
    //    rabbitTemplate.convertAndSend("chat.exchange", "private", message.getMessage());

        if(!chatHistoryService.checkIfChatHistoryExists(message.getSenderId(), message.getReceiverId())){
            chatHistoryService.newChatHistory(message.getSenderId(), message.getReceiverId(), message.getMessage(), message.getDate());
        }
        else{
            chatHistoryService.updateChatHistory(message.getSenderId(), message.getReceiverId(), message.getMessage(), message.getDate());
        }
        simpMessagingTemplate.convertAndSendToUser(String.valueOf(message.getReceiverId()),"/private",message);

        System.out.println(message.toString());
        return message;
    }
}
