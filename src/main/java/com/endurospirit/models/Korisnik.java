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
    private Long ID;
    @Size(min=3, max=20, message="Polje ime mora imati izmedu 3 i 20 znakova")
    @NotBlank(message="Polje ime je obavezno")
    String Ime;
    @Size(min=3, max=20, message="Polje prezime mora imati izmedu 3 i 20 znakova")
    @NotBlank(message="Polje prezime je obavezno")
    String Prezime;
    @NotBlank(message = "Polje email je obavezno")
    @Email(message = "Email adresa mora biti ispravnog fonta")
    String Email;
    @NotBlank(message = "Polje adresa je obavezno")
    String Adresa;
    String Telefon;
    @NotBlank(message = "Polje je obavezno")
    String DatumRodenja;
    @NotBlank(message = "Polje je obavezno")
    String VozackeSposobnosti;
    @NotBlank(message = "Polje je obavezno")
    String VelicinaOdjece;
    @NotNull(message = "Polje je obavezno")
    Long VelicinaObuce;
    @NotBlank(message = "Polje je obavezno")
    String VelicinaKacige;
    @NotNull(message = "Polje je obavezno")
    Long Tezina;
    @NotNull(message = "Polje je obavezno")
    Long Visina;
    String Role;
    @NotBlank(message = "Polje je obavezno")
    String Lozinka;
    @NotBlank(message = "Polje je obavezno")
    @Transient
    String PotvrdaLozinke;
    public Korisnik() {
    }

    public Korisnik(String ime, String prezime, String email, String adresa, String telefon, String datumRodenja, String vozackeSposobnosti, String velicinaOdjece, Long velicinaObuce, String velicinaKacige, Long tezina, Long visina, String role, String lozinka, String potvrdaLozinke) {
        Ime = ime;
        Prezime = prezime;
        Email = email;
        Adresa = adresa;
        Telefon = telefon;
        DatumRodenja = datumRodenja;
        VozackeSposobnosti = vozackeSposobnosti;
        VelicinaOdjece = velicinaOdjece;
        VelicinaObuce = velicinaObuce;
        VelicinaKacige = velicinaKacige;
        Tezina = tezina;
        Visina = visina;
        Role = role;
        Lozinka = lozinka;
        PotvrdaLozinke = potvrdaLozinke;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getIme() {
        return Ime;
    }

    public void setIme(String ime) {
        Ime = ime;
    }

    public String getPrezime() {
        return Prezime;
    }

    public void setPrezime(String prezime) {
        Prezime = prezime;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAdresa() {
        return Adresa;
    }

    public void setAdresa(String adresa) {
        Adresa = adresa;
    }

    public String getTelefon() {
        return Telefon;
    }

    public void setTelefon(String telefon) {
        Telefon = telefon;
    }

    public String getDatumRodenja() {
        return DatumRodenja;
    }

    public void setDatumRodenja(String datumRodenja) {
        DatumRodenja = datumRodenja;
    }

    public String getVozackeSposobnosti() {
        return VozackeSposobnosti;
    }

    public void setVozackeSposobnosti(String vozackeSposobnosti) {
        VozackeSposobnosti = vozackeSposobnosti;
    }

    public String getVelicinaOdjece() {
        return VelicinaOdjece;
    }

    public void setVelicinaOdjece(String velicinaOdjece) {
        VelicinaOdjece = velicinaOdjece;
    }

    public Long getVelicinaObuce() {
        return VelicinaObuce;
    }

    public void setVelicinaObuce(Long velicinaObuce) {
        VelicinaObuce = velicinaObuce;
    }

    public String getVelicinaKacige() {
        return VelicinaKacige;
    }

    public void setVelicinaKacige(String velicinaKacige) {
        VelicinaKacige = velicinaKacige;
    }

    public Long getTezina() {
        return Tezina;
    }

    public void setTezina(Long tezina) {
        Tezina = tezina;
    }

    public Long getVisina() {
        return Visina;
    }

    public void setVisina(Long visina) {
        Visina = visina;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getLozinka() {
        return Lozinka;
    }

    public void setLozinka(String lozinka) {
        Lozinka = lozinka;
    }
    public String getPotvrdaLozinke(){
        return PotvrdaLozinke;
    }
    public void setPotvrdaLozinke(String potvrdaLozinke){
        PotvrdaLozinke=potvrdaLozinke;
    }
    @AssertTrue(message = "Lozinke se moraju podudarati")
    public boolean isPasswordsEqual(){
        try{
            return this.Lozinka.equals(this.PotvrdaLozinke);
        }catch (Exception e){
            return false;
        }
    }
    @OneToMany(mappedBy = "korisnik")
    private List<BookingLogs> bookingLogs=new ArrayList<>();
}
