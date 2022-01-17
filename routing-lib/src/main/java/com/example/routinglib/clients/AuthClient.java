package com.example.routinglib.clients;

import com.example.routinglib.dtos.AuthRequestDto;
import com.example.routinglib.dtos.AuthResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("ms-auth")
public interface AuthClient {

    @GetMapping("/auth/login")
    AuthResponseDto login(@RequestBody AuthRequestDto request);


}
