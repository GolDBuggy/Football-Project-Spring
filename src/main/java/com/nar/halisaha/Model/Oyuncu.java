package com.nar.halisaha.Model;

import antlr.Token;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "nar_calisan")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Oyuncu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "player_pass")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "roles")
    private String roles;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "statistic")
    private double statistic;

    @Column(name = "mevki")
    private String mevki;

    @OneToOne(mappedBy = "oyuncu",cascade = CascadeType.ALL)
    private VerificationToken token;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "events",joinColumns = @JoinColumn(name = "player_id"),inverseJoinColumns = @JoinColumn(name = "match_id"))
    private List<Match> matches;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "playerId")
    private List<Points> points;





}
