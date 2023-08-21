package com.pkinc.messagingservice.ServiceCommunication;




import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.pkinc.messagingservice.Response.JwtAuthenticationResponse;





@FeignClient(name = "auth-service")  
public interface AuthService {
    @GetMapping("/api/v1/auth/validateToken")
    ResponseEntity<?> verifyToken(@RequestHeader HttpHeaders headers);

    @GetMapping("api/v1/auth/identifyToken")
    JwtAuthenticationResponse identifyToken (@RequestHeader HttpHeaders headers);
    
     default HttpHeaders getHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
      
        headers.add("Authorization", token);
        return headers;
    }
    
}
