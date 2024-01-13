package com.endurospirit.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Tura {
    @Id
    @GeneratedValue
    private Long ID;

    Date PocetakTure;
    Date ZavrsetakTure;
    Boolean TrajanjeTure;
    Long BrojMjesta;
    Long BrojPopunjenihMjesta;

    public Tura() {
    }

    public Tura(Date pocetakTure, Date zavrsetakTure, Boolean trajanjeTure, Long brojMjesta, Long brojPopunjenihMjesta) {
        PocetakTure = pocetakTure;
        ZavrsetakTure = zavrsetakTure;
        TrajanjeTure = trajanjeTure;
        BrojMjesta = brojMjesta;
        BrojPopunjenihMjesta = brojPopunjenihMjesta;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Date getPocetakTure() {
        return PocetakTure;
    }

    public void setPocetakTure(Date pocetakTure) {
        PocetakTure = pocetakTure;
    }

    public Date getZavrsetakTure() {
        return ZavrsetakTure;
    }

    public void setZavrsetakTure(Date zavrsetakTure) {
        ZavrsetakTure = zavrsetakTure;
    }

    public Boolean getTrajanjeTure() {
        return TrajanjeTure;
    }

    public void setTrajanjeTure(Boolean trajanjeTure) {
        TrajanjeTure = trajanjeTure;
    }

    public Long getBrojMjesta() {
        return BrojMjesta;
    }

    public void setBrojMjesta(Long brojMjesta) {
        BrojMjesta = brojMjesta;
    }

    public Long getBrojPopunjenihMjesta() {
        return BrojPopunjenihMjesta;
    }

    public void setBrojPopunjenihMjesta(Long brojPopunjenihMjesta) {
        BrojPopunjenihMjesta = brojPopunjenihMjesta;
    }
    @OneToMany(mappedBy = "tura")
    private List<BookingLogs> bookingLogs=new ArrayList<>();
}
