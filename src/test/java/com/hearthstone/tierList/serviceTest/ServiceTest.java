package com.hearthstone.tierList.serviceTest;

import com.hearthstone.tierList.service.TierListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}
