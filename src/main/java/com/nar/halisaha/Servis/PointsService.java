package com.nar.halisaha.Servis;

import com.nar.halisaha.DTO.OyuncuDto;
import com.nar.halisaha.Model.Oyuncu;
import com.nar.halisaha.Model.Points;
import com.nar.halisaha.Repo.PointsRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PointsService {

    private final PointsRepo repo;
    private final OyuncuServis oyuncuServis;

    public List<Points> findAll(){
        return repo.findAll();
    }

    public void save(OyuncuDto oyuncuDto, String email){
        repo.save(createPoints(oyuncuDto,email));
    }


    public Double getStatistic(Oyuncu oyuncu){
        Double total=(double)sumOfList(oyuncu)/10;
        return total;
    }

    private int sumOfList(Oyuncu oyuncu){
        int sum=repo.totalPoint(oyuncu).stream().mapToInt(Integer::intValue).sum();
        return sum;
    }

    private Points createPoints(OyuncuDto oyuncuDto, String email){
        Points points=new Points();
        points.setPlayerId(oyuncuServis.getById(oyuncuDto.getId()));
        points.setPlayerPoint((int) Math.floor(oyuncuDto.getStatistic()));
        points.setVoterMail(email);
        points.setMatchId(oyuncuDto.getMatchId());

        if (repo.existsPointsByPlayerIdAndVoterMailAndAndMatchId(points.getPlayerId(),points.getVoterMail(),points.getMatchId()))
            throw new RuntimeException("Zaten oy kullanıldı!");

        return points;
    }


}
