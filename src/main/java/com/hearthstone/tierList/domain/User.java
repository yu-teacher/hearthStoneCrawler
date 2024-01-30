package com.hearthstone.tierList.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
    @Id
    private Long UserRanking;
    @Column
    private String BattleTag;
    @Column
    private float AvgWins;

}
