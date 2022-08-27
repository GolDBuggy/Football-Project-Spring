package com.nar.halisaha.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OyuncuDto {

    private long id;
    private double statistic;
    private int matchId;
}
