package com.nar.halisaha.Repo;

import com.nar.halisaha.Model.Oyuncu;
import com.nar.halisaha.Model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepo extends JpaRepository<VerificationToken,Long> {

    public VerificationToken findByToken(String token);

    public VerificationToken findByOyuncu(Oyuncu oyuncu);

}
