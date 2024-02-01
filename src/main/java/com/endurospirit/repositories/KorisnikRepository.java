package com.endurospirit.repositories;

import com.endurospirit.models.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
    @Query("SELECT u FROM Korisnik u WHERE u.email=?1")
    public Korisnik findByEmail(String email);
}
