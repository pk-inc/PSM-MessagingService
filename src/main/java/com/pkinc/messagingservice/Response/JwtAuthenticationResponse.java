package com.pkinc.messagingservice.Response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private Profile profile;

    private Role role;

    public JwtAuthenticationResponse(String accessToken,Profile profile,Role role) {
        this.accessToken = accessToken;
        this.profile = profile;
        this.role = role;
    }


}