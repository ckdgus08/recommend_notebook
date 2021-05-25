package com.github.ckdgus08.repository;

import com.github.ckdgus08.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserId(String userId);
}
