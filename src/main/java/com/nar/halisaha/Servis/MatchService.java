package com.nar.halisaha.Servis;

import com.nar.halisaha.DTO.MatchDto;
import com.nar.halisaha.Model.Match;
import com.nar.halisaha.Model.Oyuncu;
import com.nar.halisaha.Repo.MatchRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepo repo;
    private final OyuncuServis servis;

    private static Logger logger=Logger.getLogger(MatchService.class.getName());

    public List<Match> allMatches(){
        return repo.findAll();
    }


    public List<MatchDto> readyMatch(){
        List<MatchDto> matchDtos=new ArrayList<>(repo.getMatchesByTeamCreated(true).size());
        repo.getMatchesByTeamCreated(true).forEach(
                o -> {

                    matchDtos.add(new MatchDto(o.getId(),o.getMatchName(),o.getMatchDate(),
                            o.getPlayers().subList(0,o.getPlayers().size()/2),o.getPlayers().subList(o.getPlayers().size()/2,o.getPlayers().size())));
                });


        return matchDtos;
    }

    public void save(Match match){
        repo.save(match);
    }

    public Match getById(long id){
        return repo.findById(id).get();
    }


    public void addPlayerToMatch(long matchId,String email){
        Match match=getById(matchId);
        match.getPlayers().add(servis.getByEmail(email).get());
        repo.save(match);
    }


    public MatchDto matchPlayer(long id){
        Match match=getById(id);
        match.setPlayers(matches(match.getPlayers()));
        repo.save(match);
        MatchDto matchDto=MatchDto.builder().matchDate(match.getMatchDate()).
                matchName(match.getMatchName()).id(match.getId()).team1(match.getPlayers().subList(0,5))
                .team2(match.getPlayers().subList(match.getPlayers().size()/2,match.getPlayers().size())).build();
        return matchDto;
    }


    private List<Oyuncu> matches(List<Oyuncu> players){
        players=createTeam(players);
        return players;
    }


    private List<Oyuncu> createTeam(List<Oyuncu> players){
        boolean key=true;
        while (key){
            Collections.shuffle(players);
           if(controlStatics(players) && controlPlayerPosition(players)){
               key=false;
            }
        }
        return players;
    }



    private boolean controlPlayerPosition(List<Oyuncu> players){
        AtomicBoolean key = new AtomicBoolean(false);
        AtomicBoolean key1 = new AtomicBoolean(false);
        int teamRange= players.size()/2;
        players.subList(0,teamRange).stream().forEach(p -> key.set(p.getMevki().equals("Kale")));
        players.subList(teamRange,players.size()).stream().forEach(t -> key1.set(t.getMevki().equals("Kale")));
        return key.get()&& key1.get();
    }

    private boolean controlStatics(List<Oyuncu> player){
        double team1Avg= player.subList(0,player.size()/2).stream().mapToDouble(Oyuncu::getStatistic).sum();
        double team2Avg=player.subList(player.size()/2,player.size()).stream().mapToDouble(Oyuncu::getStatistic).sum();

        if(Math.abs(team1Avg-team2Avg) > 1)
            return false;

    return true;
    }


    /*
     Collections.sort(players,(o1,o2) -> Double.compare(o2.getStatistic(),o1.getStatistic()));
        List<Oyuncu> team1=new ArrayList<>();
        List<Oyuncu> team2=new ArrayList<>();
        List<Oyuncu> oyuncus=new ArrayList<>();
        players.stream().forEach(i ->{
            logger.info(i.getMevki()+"");
            if (i.getMevki().matches("Kale")){
                oyuncus.add(i);
            }

        });
        team1.add(players.get(0));
        team2.add(oyuncus.get(0));
        team1.add(oyuncus.get(1));
        Boolean isAdded = false;
        for (int i = 3; i <players.size() ; i+=2) {
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
     */

}
