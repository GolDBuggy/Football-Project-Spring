package com.nar.halisaha.Servis;

import com.nar.halisaha.Model.Oyuncu;
import com.nar.halisaha.Repo.OyuncuRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerDetailService implements UserDetailsService {

    private final OyuncuRepo repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Oyuncu> employee=repo.findByEmail(email);
        return employee.map(PlayerDetails::new).orElseThrow(() -> new UsernameNotFoundException("Kullanıcı Bulunmadı!"));
    }
}
