package com.hearthstone.tierList.repository;

import com.hearthstone.tierList.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

}
