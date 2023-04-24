package com.frombelow.web.app.service;

import com.frombelow.web.app.entity.User;
import com.frombelow.web.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    
    public User singUp(User user) {
        if (existsByUsername(user.getUsername())) {
            return new User();
        }

        user.setPass(passwordEncoder.encode(user.getPass()));
        return userRepository.save(user);
    }

    public User loadByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
