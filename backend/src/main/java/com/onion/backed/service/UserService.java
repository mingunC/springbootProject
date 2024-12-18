package com.onion.backed.service;

import com.onion.backed.entity.User;
import com.onion.backed.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 유저 생성 메서드
    public User createUser(String username, String password, String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        //user.setCreateDate(LocalDateTime.now()); // 생성일 설정 (Auditing 사용 시 생략 가능)
        //user.setUpdateDate(LocalDateTime.now()); // 업데이트 시간 설정 (Auditing 사용 시 생략 가능)

        return userRepository.save(user);
    }
}

