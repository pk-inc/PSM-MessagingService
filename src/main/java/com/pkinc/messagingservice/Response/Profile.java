package com.pkinc.messagingservice.Response;




import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Profile {

    private Long userId;
    private String email;
    private String name;
    private String phoneNumber;
    
}

