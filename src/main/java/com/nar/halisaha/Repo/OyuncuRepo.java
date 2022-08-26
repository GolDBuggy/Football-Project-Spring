package com.nar.halisaha.Repo;

import com.nar.halisaha.Model.Oyuncu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OyuncuRepo extends JpaRepository<Oyuncu,Long> {

    Optional<Oyuncu> findByEmail(String email);

    @Query("select o from Oyuncu o where o.email<>:param")
    List<Oyuncu> hariciGetir(@Param("param") String email);
}
