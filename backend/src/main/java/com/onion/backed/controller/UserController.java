package com.onion.backed.controller;

import com.onion.backed.entity.User;
import com.onion.backed.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 유저 생성 API
    @PostMapping("")
    public ResponseEntity<User> createUser(@RequestParam String username,@RequestParam String password, @RequestParam String email) {
        User user = userService.createUser(username, password, email);
        return ResponseEntity.ok(user);
    }

    // 유저 삭제 API
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "ID of the user to be deleted ", required = true)
            @PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}

