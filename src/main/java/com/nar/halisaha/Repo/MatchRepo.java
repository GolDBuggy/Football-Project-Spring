package com.nar.halisaha.Repo;

import com.nar.halisaha.Model.Match;
import com.nar.halisaha.Model.Oyuncu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Iterator;
import java.util.List;

public interface MatchRepo extends JpaRepository<Match,Long> {

    @Query("select u.players from Match u where u.id=:param1")
    List<Oyuncu> playersGetById(@Param("param1") long id);

    @Query("select u from Match u where u.teamCreated=:param1 ")
    List<Match> readyTeam(@Param("param1") String x);

    List<Match> getMatchesByTeamCreated(boolean key);
}
