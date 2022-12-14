package com.nar.halisaha.Controller;

import com.nar.halisaha.DTO.OyuncuDto;
import com.nar.halisaha.DTO.RegisterDTO;
import com.nar.halisaha.Model.Oyuncu;
import com.nar.halisaha.Model.VerificationToken;
import com.nar.halisaha.Servis.MatchService;
import com.nar.halisaha.Servis.OyuncuServis;
import com.nar.halisaha.Servis.PointsService;
import com.nar.halisaha.Servis.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
@RequiredArgsConstructor
public class OyuncuController {

    private final OyuncuServis service;
    private final PointsService pointsService;
    private final VerificationTokenService tokenService;

    private static Logger logger=Logger.getLogger(OyuncuController.class.getName());


    @GetMapping("/")
    public String page(Model model){
        model.addAttribute("user",new RegisterDTO());
        return "register-page";
    }

    @GetMapping("/list")
    public String all(Model model){
        model.addAttribute("players",service.getAll());
        return "players";
    }

    @PostMapping("/register")
    public String save(@ModelAttribute("user") RegisterDTO registerDTO,Model model){
        service.saveOyuncu(registerDTO);
        Optional<Oyuncu> player=service.getByEmail(registerDTO.getEmail());
        VerificationToken verificationToken=tokenService.findByOyuncu(player.get());
        String url= registerDTO.getEmail()+" adresinize aktivasyon maili gönderildi!";
        model.addAttribute("link",url);
        return "verification";
    }

    @GetMapping("/player")
    public String getPlayerById(@RequestParam("profile") String id,Model model, Principal principal){
        model.addAttribute("oyuncular",service.hariciGetir(principal.getName()));
        model.addAttribute("oyuncu",new OyuncuDto());
        model.addAttribute("matchId",id);
        return "PlayerProfile";
    }

    /*
      @GetMapping("/player")
    public String getPlayerById(@RequestParam("profile") String email, Model model, Principal principal){
        model.addAttribute("oyuncular",service.getByEmail(email).get());

        return "PlayerProfile";
    }

     */

    @PostMapping("/save")
    public String pointSave(@ModelAttribute("oyuncu") OyuncuDto oyuncuDto,Principal principal){
        pointsService.save(oyuncuDto,principal.getName());
        Oyuncu oyuncu=service.getById(oyuncuDto.getId());
        oyuncu.setStatistic(pointsService.getStatistic(oyuncu));
        service.save(oyuncu);
        return "redirect:/getir";
    }

    @GetMapping("/activation")
    public String activation(@RequestParam("token") String token, Model model){
        VerificationToken verificationToken= tokenService.findByToken(token);
        if(verificationToken==null){
            model.addAttribute("message","Your token is invalid!");
        }else{
            Oyuncu oyuncu=verificationToken.getOyuncu();
            if (!oyuncu.isActive()){
                oyuncu.setActive(true);
                service.save(oyuncu);
            }
        }

        return "redirect:/login";
    }

}
