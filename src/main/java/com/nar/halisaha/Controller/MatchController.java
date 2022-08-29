package com.nar.halisaha.Controller;

import com.nar.halisaha.Model.Match;
import com.nar.halisaha.Servis.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Logger;

@Controller
@RequiredArgsConstructor
public class MatchController {

    private final MatchService service;

    private static Logger logger=Logger.getLogger(MatchController.class.getName());

    @GetMapping("/getir")
    public String getAll(@RequestParam("matchId") long id,Model model){
        model.addAttribute("matches",service.matchPlayer(id));
        return "matches";
    }

    @GetMapping("/olustur")
    public String createMatch(Model model){
        model.addAttribute("match",new Match());
        return "create-match";
    }


    @PostMapping("/create")
    public String saveMatch(@ModelAttribute("match") Match match){
        logger.info(match.getMatchDate()+"  "+match.getMatchName());
        service.save(match);
        return "redirect:/getir";
    }

    @GetMapping("/all")
    public String getAllMatch(Model model){
        model.addAttribute("allMatch",service.allMatches());

        return "all-match";
    }





}
