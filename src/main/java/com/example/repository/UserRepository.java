package com.example.jehunonboarding.repository;

import com.example.jehunonboarding.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {
}
