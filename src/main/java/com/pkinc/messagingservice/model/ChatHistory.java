package com.pkinc.messagingservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table()
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChatHistory {
    @Id
    @SequenceGenerator(name = "chat_history_sequence",
            sequenceName = "chat_history_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "chat_history_sequence"
    )
    private Long chatHistoryId;
    private Long senderId;
    private Long receiverId;
    private String lastMessage;
    private String lastMessageDate;
    
    
}
