package com.hearthstone.tierList.controller;

import com.hearthstone.tierList.domain.User;
import com.hearthstone.tierList.service.TierListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TierListController {

    private final TierListService tierListService;

    @GetMapping("tierList")
    public ResponseEntity<Page<User>> allPages(
            @RequestParam(name = "pageNumber", required = false, defaultValue = "1") Integer pageNumber){
        return new ResponseEntity<>(tierListService.allPages(pageNumber), HttpStatus.OK);
    }

    @GetMapping("tagSearch")
    public ResponseEntity<Page<User>> tagSearch(
            @RequestParam(name = "battleTag") String battleTag,
            @RequestParam(name = "pageNumber", required = false, defaultValue = "1") Integer pageNumber){
        return new ResponseEntity<>(tierListService.searchTagUserData(battleTag,pageNumber),HttpStatus.OK);
    }
}
