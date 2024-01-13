package com.endurospirit.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class BookingLogs {
    @Id
    @GeneratedValue
    private Long ID;
    @ManyToOne
    @JoinColumn(name = "koisnik_id")
    private Korisnik korisnik;
    @ManyToOne
    @JoinColumn(name = "tura_id")
    private Tura tura;
    Date DatumiVrijeme;
    Boolean TipTure;
    String NacinDolaska;
    Boolean TrebamMotocikl;
    Boolean TrebamOpremu;
    String Poruka;

    public BookingLogs() {
    }

    public BookingLogs(Date datumiVrijeme, Boolean tipTure, String nacinDolaska, Boolean trebamMotocikl, Boolean trebamOpremu, String poruka) {
        DatumiVrijeme = datumiVrijeme;
        TipTure = tipTure;
        NacinDolaska = nacinDolaska;
        TrebamMotocikl = trebamMotocikl;
        TrebamOpremu = trebamOpremu;
        Poruka = poruka;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
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

    public Date getDatumiVrijeme() {
        return DatumiVrijeme;
    }

    public void setDatumiVrijeme(Date datumiVrijeme) {
        DatumiVrijeme = datumiVrijeme;
    }

    public Boolean getTipTure() {
        return TipTure;
    }

    public void setTipTure(Boolean tipTure) {
        TipTure = tipTure;
    }

    public String getNacinDolaska() {
        return NacinDolaska;
    }

    public void setNacinDolaska(String nacinDolaska) {
        NacinDolaska = nacinDolaska;
    }

    public Boolean getTrebamMotocikl() {
        return TrebamMotocikl;
    }

    public void setTrebamMotocikl(Boolean trebamMotocikl) {
        TrebamMotocikl = trebamMotocikl;
    }

    public Boolean getTrebamOpremu() {
        return TrebamOpremu;
    }

    public void setTrebamOpremu(Boolean trebamOpremu) {
        TrebamOpremu = trebamOpremu;
    }

    public String getPoruka() {
        return Poruka;
    }

    public void setPoruka(String poruka) {
        Poruka = poruka;
    }
}
