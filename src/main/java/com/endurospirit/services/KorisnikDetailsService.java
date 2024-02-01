package com.endurospirit.services;

import com.endurospirit.models.Korisnik;
import com.endurospirit.repositories.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class KorisnikDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    KorisnikRepository repository;

    @Override
    public com.endurospirit.models.KorisnikDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Korisnik u=repository.findByEmail(username);

        return new com.endurospirit.models.KorisnikDetails(u);
    }
}
