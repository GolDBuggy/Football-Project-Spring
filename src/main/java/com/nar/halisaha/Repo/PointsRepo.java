package com.nar.halisaha.Repo;

import com.nar.halisaha.Model.Oyuncu;
import com.nar.halisaha.Model.Points;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PointsRepo extends JpaRepository<Points,Long> {

    @Query("select u.playerPoint from Points u where u.playerId=:param")
    List<Integer> totalPoint(@Param("param")Oyuncu oyuncu);

    boolean existsPointsByPlayerIdAndVoterMailAndAndMatchId(Oyuncu id,String mail,int matchId);
}
