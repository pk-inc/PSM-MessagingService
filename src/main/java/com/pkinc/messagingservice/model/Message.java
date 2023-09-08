package com.pkinc.messagingservice.model;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Message {
    private String senderName;
    private String receiverName;
    private Long senderId;
    private Long receiverId;
    private String message;
    private String date;
    private Status status;
}
