package com.rinhaback.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rinhaback.api.domain.User.User;

public interface UserRepository extends JpaRepository<User, UUID>{

}
