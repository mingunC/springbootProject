package com.onion.backed.repository;

import com.onion.backed.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 기본적으로 JpaRepository의 save() 메서드를 활용해 User 엔티티를 저장할 수 있습니다.
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}


