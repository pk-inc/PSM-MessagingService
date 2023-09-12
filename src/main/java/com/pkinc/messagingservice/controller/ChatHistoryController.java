package com.pkinc.messagingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pkinc.messagingservice.Response.PagedResponse;
import com.pkinc.messagingservice.Service.ChatHistoryService;
import com.pkinc.messagingservice.config.AppConstants;

@RestController()
@RequestMapping("/api/v1/chatHistory")
public class ChatHistoryController {
     @Autowired
    ChatHistoryService chatHistoryService;


    @GetMapping("/{userId}")
    public PagedResponse<?> getAllUsersChatHistory(@PathVariable Long userId, @RequestParam(name = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
    @RequestParam(name = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
    @RequestParam(name = "sort" ,defaultValue = "DESC") String sort){
        return chatHistoryService.getAllUsersChatHistory(userId, page, size, sort);
    }
}
