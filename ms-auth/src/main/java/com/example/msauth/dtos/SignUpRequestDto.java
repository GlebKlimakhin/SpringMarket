package com.example.msauth.dtos;

import lombok.Data;

@Data
public class SignUpRequestDto {
    private String email;
    private String password;
}
