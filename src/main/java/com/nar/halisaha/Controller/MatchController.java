package com.nar.halisaha.Controller;

import com.nar.halisaha.Servis.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MatchController {

    private final MatchService service;

    @GetMapping("/getir")
    public String getAll(Model model){
        model.addAttribute("matches",service.matchPlayer());

        return "matches";
    }





}
