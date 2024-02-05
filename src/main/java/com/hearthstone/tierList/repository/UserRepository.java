package com.hearthstone.tierList.repository;

import com.hearthstone.tierList.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Page<User> findByBattleTagContaining(String battleTag, Pageable pageable);
}
