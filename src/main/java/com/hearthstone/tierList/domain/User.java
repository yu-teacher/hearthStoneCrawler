package com.hearthstone.tierList.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private Long userRanking;
    @Column(nullable = false,length = 64)
    private String battleTag;
    @Column(nullable = false)
    private double avgWins;

    @Override
    public String toString() {
        return "User{" +
                "userRanking=" + userRanking +
                ", battleTag='" + battleTag + '\'' +
                ", avgWins=" + avgWins +
                '}';
    }
}
