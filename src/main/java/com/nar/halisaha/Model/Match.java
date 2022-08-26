package com.nar.halisaha.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "football_match")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private long id;

    @Column(name = "match_name")
    private String matchName;

    @Column(name = "match_date")
    private LocalDate matchDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "events",joinColumns = @JoinColumn(name = "match_id"),inverseJoinColumns = @JoinColumn(name = "player_id"))
    private List<Oyuncu> players;
}
