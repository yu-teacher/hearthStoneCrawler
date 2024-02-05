package com.hearthstone.tierList.serviceTest;

import com.hearthstone.tierList.domain.User;
import com.hearthstone.tierList.service.TierListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

@SpringBootTest
public class ServiceTest {

    private final TierListService tierListService;

    @Autowired
    public ServiceTest(TierListService tierListService) {
        this.tierListService = tierListService;
    }

    @Test
    public void 불러오기(){
        tierListService.callTierList();
    }

    @Test
    public void 페이지검색(){
        Page<User> users = tierListService.allPages(1);
        System.out.println(users.getContent());
    }

    @Test
    public void 검색기능(){
        Page<User> users = tierListService.searchTagUserData("Flurry",1);
        System.out.println(users.getContent());
    }
}
