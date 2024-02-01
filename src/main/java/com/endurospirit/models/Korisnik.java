package com.endurospirit.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Korisnik {
    @Id
    @GeneratedValue
    private Long id;
    @Size(min=3, max=20, message="Polje ime mora imati izmedu 3 i 20 znakova")
    @NotBlank(message="Polje ime je obavezno")
    String ime;
    @Size(min=3, max=20, message="Polje prezime mora imati izmedu 3 i 20 znakova")
    @NotBlank(message="Polje prezime je obavezno")
    String prezime;
    @NotBlank(message = "Polje email je obavezno")
    @Email(message = "Email adresa mora biti ispravnog fonta")
    String email;
    @NotBlank(message = "Polje adresa je obavezno")
    String adresa;
    @NotBlank(message = "Polje je obavezno")
    @Pattern(regexp = "[0-9]+", message = "Telefon mora sadržavati samo brojeve")
    String telefon;
    @NotBlank(message = "Polje je obavezno")
    String datumRodenja;
    @NotBlank(message = "Polje je obavezno")
    String vozackeSposobnosti;
    @NotBlank(message = "Polje je obavezno")
    String velicinaOdjece;
    @NotNull(message = "Polje je obavezno")
    Long velicinaObuce;
    @NotBlank(message = "Polje je obavezno")
    String velicinaKacige;
    @NotNull(message = "Polje je obavezno")
    Long tezina;
    @NotNull(message = "Polje je obavezno")
    Long visina;
    String role;
    @NotBlank(message = "Polje je obavezno")
    String lozinka;
    @NotBlank(message = "Polje je obavezno")
    @Transient
    String potvrdaLozinke;
    public Korisnik() {
    }

    public Korisnik(Long id, String ime, String prezime, String email, String adresa, String telefon, String datumRodenja, String vozackeSposobnosti, String velicinaOdjece, Long velicinaObuce, String velicinaKacige, Long tezina, Long visina, String role, String lozinka, String potvrdaLozinke) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.adresa = adresa;
        this.telefon = telefon;
        this.datumRodenja = datumRodenja;
        this.vozackeSposobnosti = vozackeSposobnosti;
        this.velicinaOdjece = velicinaOdjece;
        this.velicinaObuce = velicinaObuce;
        this.velicinaKacige = velicinaKacige;
        this.tezina = tezina;
        this.visina = visina;
        this.role = role;
        this.lozinka = lozinka;
        this.potvrdaLozinke = potvrdaLozinke;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getDatumRodenja() {
        return datumRodenja;
    }

    public void setDatumRodenja(String datumRodenja) {
        this.datumRodenja = datumRodenja;
    }

    public String getVozackeSposobnosti() {
        return vozackeSposobnosti;
    }

    public void setVozackeSposobnosti(String vozackeSposobnosti) {
        this.vozackeSposobnosti = vozackeSposobnosti;
    }

    public String getVelicinaOdjece() {
        return velicinaOdjece;
    }

    public void setVelicinaOdjece(String velicinaOdjece) {
        this.velicinaOdjece = velicinaOdjece;
    }

    public Long getVelicinaObuce() {
        return velicinaObuce;
    }

    public void setVelicinaObuce(Long velicinaObuce) {
        this.velicinaObuce = velicinaObuce;
    }

    public String getVelicinaKacige() {
        return velicinaKacige;
    }

    public void setVelicinaKacige(String velicinaKacige) {
        this.velicinaKacige = velicinaKacige;
    }

    public Long getTezina() {
        return tezina;
    }

    public void setTezina(Long tezina) {
        this.tezina = tezina;
    }

    public Long getVisina() {
        return visina;
    }

    public void setVisina(Long visina) {
        this.visina = visina;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }
    public String getPotvrdaLozinke(){
        return potvrdaLozinke;
    }
    public void setPotvrdaLozinke(String potvrdaLozinke){
        this.potvrdaLozinke=potvrdaLozinke;
    }
    @AssertTrue(message = "Lozinke se moraju podudarati")
    public boolean isPasswordsEqual(){
        try{
            return this.lozinka.equals(this.potvrdaLozinke);
        }catch (Exception e){
            return false;
        }
    }
    @OneToMany(mappedBy = "korisnik")
    private List<BookingLogs> bookingLogs=new ArrayList<>();
}
