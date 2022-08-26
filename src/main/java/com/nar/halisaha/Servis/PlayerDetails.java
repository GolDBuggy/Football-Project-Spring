package com.nar.halisaha.Servis;

import com.nar.halisaha.Model.Oyuncu;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerDetails implements UserDetails {

    private String email;
    private String password;
    private List<GrantedAuthority> roles;
    private boolean isActive;


    public PlayerDetails(Oyuncu player){
        email=player.getEmail();
        password=player.getPassword();
        roles= Arrays.stream(player.getRoles().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        isActive=player.isActive();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
}
