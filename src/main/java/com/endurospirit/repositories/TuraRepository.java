package com.endurospirit.repositories;

import com.endurospirit.models.Korisnik;
import com.endurospirit.models.Tura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TuraRepository extends JpaRepository<Tura, Long> {
    @Query("SELECT t FROM Tura t WHERE t.brojPopunjenihMjesta<t.brojMjesta")
    List<Tura> findByBrojMjestaGreaterThan();


}