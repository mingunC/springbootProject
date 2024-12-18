package com.onion.backed.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // ID/PW 방식의 로그인을 위한 username, password
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    // 이메일 필드
    @Column(nullable = false)
    private String email;

    // 최근 로그인 일시
    private LocalDateTime lastLogin;

    // 유저 생성일 (최초 Insert 시점 자동 등록)
    @CreatedDate
    @Column(insertable = true)
    private LocalDateTime createDate;

    // 갱신일 (정보 변경 시점 자동 업데이트)
    @LastModifiedDate
    private LocalDateTime updateDate;
}

