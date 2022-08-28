package com.nar.halisaha.Servis;

import com.nar.halisaha.DTO.RegisterDTO;
import com.nar.halisaha.Mail.EmailService;
import com.nar.halisaha.Model.Oyuncu;
import com.nar.halisaha.Repo.OyuncuRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OyuncuServis {

    private final OyuncuRepo repo;
    private final VerificationTokenService tokenService;
    private final EmailService service;
    private final BCryptPasswordEncoder encoder;


    private static String DEFAULT_ROLE="ROLE_USER";

    public List<Oyuncu> getAll(){
        return repo.findAll();
    }

    public Oyuncu getById(long id){
        return repo.findById(id).get();
    }

    public void save(Oyuncu player){
        repo.save(player);
    }


    public Optional<Oyuncu> getByEmail(String email) {
        return repo.findByEmail(email);
    }

    public List<Oyuncu> hariciGetir(String email){
        return repo.hariciGetir(email);
    }

    public void saveOyuncu(RegisterDTO employeeDto) {
        ModelMapper modelMapper = new ModelMapper();
        checkPlayer(employeeDto);
        String pass = encoder.encode(employeeDto.getPassword());
        employeeDto.setPassword(pass);
        Oyuncu player = modelMapper.map(employeeDto, Oyuncu.class);
        player.setActive(false);
        player.setRoles(DEFAULT_ROLE);

        Optional<Oyuncu> saved = Optional.of(repo.save(player));

        saved.ifPresent(e -> {
            try {
                String token = UUID.randomUUID().toString();
                tokenService.save(saved.get(), token);
                service.sendHtmlMail(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

    }


    public void checkPlayer(RegisterDTO player) {
        if (player.getEmail().isEmpty() || player.getPassword().isEmpty()
                || player.getUsername().isEmpty() || !(player.getPassword().equals(player.getConfirmPass())))
            throw new RuntimeException("Not Null Player");
    }



}
