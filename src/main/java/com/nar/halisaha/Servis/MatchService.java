package com.nar.halisaha.Servis;

import com.nar.halisaha.DTO.MatchDto;
import com.nar.halisaha.ExceptionHandle.TeamException;
import com.nar.halisaha.Model.Match;
import com.nar.halisaha.Model.Oyuncu;
import com.nar.halisaha.Repo.MatchRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepo repo;
    private final OyuncuServis servis;

    private static Logger logger=Logger.getLogger(MatchService.class.getName());

    public List<Match> allMatches(){
        return repo.findAll();
    }

    public void save(Match match){
        repo.save(match);
    }

    public Match getById(long id){
        return repo.findById(id).get();
    }


    public MatchDto matchPlayer(){
        Match match=getById(1);
        match.setPlayers(matches(servis.getAll()));
        repo.save(match);
        MatchDto matchDto=MatchDto.builder().matchDate(match.getMatchDate()).
                matchName(match.getMatchName()).id(match.getId()).team1(match.getPlayers().subList(0,5))
                .team2(match.getPlayers().subList(5,10)).build();
        return matchDto;
    }


    public List<Oyuncu> matches(List<Oyuncu> players){
        Collections.sort(players,(o1,o2) -> Double.compare(o2.getStatistic(),o1.getStatistic()));
        List<Oyuncu> team1=new ArrayList<>();
        List<Oyuncu> team2=new ArrayList<>();
        team1.add(players.get(0));
        Boolean isAdded = false;
        for (int i = 1; i <players.size() ; i+=2) {
                if(isAdded) {
                    team1.add(players.get(i));
                    if (i + 1 < players.size()) {
                        team1.add(players.get(i + 1));
                    }
                }
               else{
                    team2.add(players.get(i));
                    if(i+1< players.size()) {
                        team2.add(players.get(i + 1));
                    }
               }
                isAdded = !isAdded;

        }
        List<Oyuncu> oyuncuList=new ArrayList<>();
        oyuncuList.addAll(team1);
        oyuncuList.addAll(team2);
        return oyuncuList;
    }


}
