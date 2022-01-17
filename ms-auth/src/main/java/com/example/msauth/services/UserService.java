package com.example.msauth.services;


import com.example.msauth.entities.Role;
import com.example.msauth.entities.User;
import com.example.msauth.repositories.IRoleRepository;
import com.example.msauth.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private IRoleRepository roleRepository;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser(User user) {
        Role role = roleRepository.findByName("ROLE_USER");
        user.setRoles(Collections.singletonList(role));
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByEmailAndPassword(String email, String password) {
        User user = findByEmail(email);
        if(user != null){
            if(encoder.matches(password, user.getPassword())){
                return user;
            }
        }
        return null;
    }

}
