package com.endurospirit.repositories;

import com.endurospirit.models.BookingLogs;
import com.endurospirit.models.Korisnik;
import com.endurospirit.models.Tura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<BookingLogs, Long> {
    List<BookingLogs> findByKorisnik(Korisnik korisnik);
    List<BookingLogs> findByTura(Tura tura);
}