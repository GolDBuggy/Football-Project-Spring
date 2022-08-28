package com.nar.halisaha.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OyuncuDto {

    private long id;
    private double statistic;
    private int matchId;
}
