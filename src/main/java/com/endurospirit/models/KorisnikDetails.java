package com.endurospirit.models;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


public class KorisnikDetails implements org.springframework.security.core.userdetails.UserDetails{
    private Korisnik korisnik;
    public KorisnikDetails(Korisnik korisnik){
        this.korisnik=korisnik;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return korisnik.getLozinka();
    }

    @Override
    public String getUsername() {
        return korisnik.getEmail();
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
        return true;
    }

    public String getFullName(){
        return korisnik.getIme()+" "+korisnik.getPrezime();
    }

    public Korisnik getKorisnik(){
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik){
        this.korisnik=korisnik;
    }
}
