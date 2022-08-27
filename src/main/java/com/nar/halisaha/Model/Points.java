package com.nar.halisaha.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "points")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Points {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Oyuncu playerId;

    @Column(name = "player_point")
    private int playerPoint;

    @Column(name = "voter_mail")
    private String voterMail;

    @Column(name = "match_id")
    private int matchId;
}
