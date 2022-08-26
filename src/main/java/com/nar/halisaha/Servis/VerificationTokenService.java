package com.nar.halisaha.Servis;

import com.nar.halisaha.Model.Oyuncu;
import com.nar.halisaha.Model.VerificationToken;
import com.nar.halisaha.Repo.TokenRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;

@Service
@RequiredArgsConstructor
public class VerificationTokenService {

    private final TokenRepo repo;

    public VerificationToken findByToken(String token){
        return repo.findByToken(token);
    }

    public VerificationToken findByOyuncu(Oyuncu oyuncu){
        return repo.findByOyuncu(oyuncu);
    }

    public void save(Oyuncu oyuncu,String token){
        VerificationToken verificationToken=new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setOyuncu(oyuncu);
        verificationToken.setExpiryDate(calculateDate(24*60));
        repo.save(verificationToken);
    }


    private Timestamp calculateDate(int timeMinutes){
        Calendar calendar=Calendar.getInstance();
        calendar.add(calendar.MINUTE,timeMinutes);

        return new Timestamp(calendar.getTime().getTime());
    }
}
