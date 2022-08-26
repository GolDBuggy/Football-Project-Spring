package com.nar.halisaha.DTO;

import com.nar.halisaha.Model.Oyuncu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchDto {

    private long id;

    private String matchName;

    private LocalDate matchDate;

    private List<Oyuncu> team1;

    private List<Oyuncu> team2;
}
