package com.example.corelib.services;

import com.example.corelib.models.UserInfo;
import org.springframework.stereotype.Component;

public interface ITokenService {

    String generateToken(UserInfo userInfo);

    UserInfo parseToken(String token);
}
