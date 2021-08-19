package com.hoaxify.ws.user.repository;

import com.hoaxify.ws.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
