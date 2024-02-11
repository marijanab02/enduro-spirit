package com.endurospirit.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Tura {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message="Polje pocetak ture je obavezno")
    String pocetakTure;

    @NotBlank(message="Polje zavrsetak ture je obavezno")
    String zavrsetakTure;

    @NotBlank(message="Polje trajanje ture je obavezno")
    String trajanjeTure;

    @NotNull(message="Polje broj mjesta je obavezno")
    Long brojMjesta;
    Long brojPopunjenihMjesta;

    public Tura() {
    }

    public Tura(Long id, String pocetakTure, String zavrsetakTure, String trajanjeTure, Long brojMjesta, Long brojPopunjenihMjesta) {
        this.id=id;
        this.pocetakTure = pocetakTure;
        this.zavrsetakTure = zavrsetakTure;
        this.trajanjeTure = trajanjeTure;
        this.brojMjesta = brojMjesta;
        this.brojPopunjenihMjesta = brojPopunjenihMjesta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPocetakTure() {
        return pocetakTure;
    }

    public void setPocetakTure(String pocetakTure) {
        this.pocetakTure = pocetakTure;
    }

    public String getZavrsetakTure() {
        return zavrsetakTure;
    }

    public void setZavrsetakTure(String zavrsetakTure) {
        this.zavrsetakTure = zavrsetakTure;
    }

    public String getTrajanjeTure() {
        return trajanjeTure;
    }

    public void setTrajanjeTure(String trajanjeTure) {
        this.trajanjeTure = trajanjeTure;
    }

    public Long getBrojMjesta() {
        return brojMjesta;
    }

    public void setBrojMjesta(Long brojMjesta) {
        this.brojMjesta = brojMjesta;
    }

    public Long getBrojPopunjenihMjesta() {
        return brojPopunjenihMjesta;
    }

    public void setBrojPopunjenihMjesta(Long brojPopunjenihMjesta) {
        this.brojPopunjenihMjesta = brojPopunjenihMjesta;
    }
    @OneToMany(mappedBy = "tura")
    private List<BookingLogs> bookingLogs=new ArrayList<>();
}