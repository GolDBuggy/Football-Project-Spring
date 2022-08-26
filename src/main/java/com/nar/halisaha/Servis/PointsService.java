package com.nar.halisaha.Servis;

import com.nar.halisaha.Model.Oyuncu;
import com.nar.halisaha.Model.Points;
import com.nar.halisaha.Repo.PointsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PointsService {

    private final PointsRepo repo;

    public List<Points> findAll(){
        return repo.findAll();
    }

    public void save(Oyuncu oyuncu){
        Points points=new Points();
        points.setPlayerId(oyuncu);
        points.setPlayerPoint((int) Math.floor(oyuncu.getStatistic()));
        repo.save(points);
    }


    public Double getStatistic(Oyuncu oyuncu){
        Double total=(double)sumOfList(oyuncu)/10;
        return total;
    }

    public int sumOfList(Oyuncu oyuncu){
        int sum=repo.totalPoint(oyuncu).stream().mapToInt(Integer::intValue).sum();

        return sum;
    }
}
