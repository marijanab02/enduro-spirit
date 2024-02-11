package com.endurospirit.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
public class BookingLogs {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "koisnik_id")
    private Korisnik korisnik;
    @ManyToOne
    @JoinColumn(name = "tura_id")
    private Tura tura;
    @NotBlank(message="Polje datum dolaska je obavezno")
    String datumiVrijeme;
    @NotBlank(message="Polje nacin dolaska ture je obavezno")
    String nacinDolaska;
    @NotNull(message="Polje je obavezno")
    Boolean trebamMotocikl;
    @NotNull(message="Polje je obavezno")
    Boolean trebamOpremu;
    String poruka;

    public BookingLogs() {
    }

    public BookingLogs(Long id, String datumiVrijeme, String nacinDolaska, Boolean trebamMotocikl, Boolean trebamOpremu, String poruka) {
        this.datumiVrijeme = datumiVrijeme;
        this.nacinDolaska = nacinDolaska;
        this.trebamMotocikl = trebamMotocikl;
        this.trebamOpremu = trebamOpremu;
        this.poruka = poruka;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Tura getTura() {
        return tura;
    }

    public void setTura(Tura tura) {
        this.tura = tura;
    }

    public String getDatumiVrijeme() {
        return datumiVrijeme;
    }

    public void setDatumiVrijeme(String datumiVrijeme) {
        this.datumiVrijeme = datumiVrijeme;
    }

    public String getNacinDolaska() {
        return nacinDolaska;
    }

    public void setNacinDolaska(String nacinDolaska) {
        this.nacinDolaska = nacinDolaska;
    }

    public Boolean getTrebamMotocikl() {
        return trebamMotocikl;
    }

    public void setTrebamMotocikl(Boolean trebamMotocikl) {
        this.trebamMotocikl = trebamMotocikl;
    }

    public Boolean getTrebamOpremu() {
        return trebamOpremu;
    }

    public void setTrebamOpremu(Boolean trebamOpremu) {
        this.trebamOpremu = trebamOpremu;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }
}