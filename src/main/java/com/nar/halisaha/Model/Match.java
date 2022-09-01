package com.nar.halisaha.Model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "football_match")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private long id;

    @Column(name = "match_name")
    private String matchName;

    @Column(name = "match_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate matchDate;

    @Column(name = "created_team")
    private Boolean teamCreated;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "events",joinColumns = @JoinColumn(name = "match_id"),inverseJoinColumns = @JoinColumn(name = "player_id"))
    private List<Oyuncu> players;
}
