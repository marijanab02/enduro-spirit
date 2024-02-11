package com.endurospirit.controllers;

import com.endurospirit.models.BookingLogs;
import com.endurospirit.models.Korisnik;
import com.endurospirit.models.KorisnikDetails;
import com.endurospirit.models.Tura;
import com.endurospirit.repositories.BookingRepository;
import com.endurospirit.repositories.TuraRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DriverController {

    @Autowired
    TuraRepository turaRepo;
    @Autowired
    BookingRepository bookingRepo;



    @GetMapping("/driver/rezervacija/{tourId}/rezervacijaForma")
    @PreAuthorize("hasAuthority('DRIVER')")
    public String showReservationForm(@PathVariable Long tourId, Model model) {
        System.out.println("Tu sam--_---------------------------------");
        Tura tura = turaRepo.findById(tourId).orElseThrow(() -> new RuntimeException("Tour not found"));

        // Add tour details to the model for the reservation form
        model.addAttribute("tura", tura);

        // Add a new BookingLogs object to the model for the reservation form
        model.addAttribute("bookingLogs", new BookingLogs());

        return "driver/rezervacijaForma";
    }

    @PostMapping("/driver/rezervacija/{tourId}/rezervacijaForma")
    @PreAuthorize("hasAuthority('DRIVER')")
    public String makeReservation(@PathVariable Long tourId, @ModelAttribute @Valid BookingLogs bookingLogs, BindingResult result, Model model, @AuthenticationPrincipal KorisnikDetails korisnikDetails) {
        if (result.hasErrors()) {
            System.out.println("error jee");
            // If there are errors, redisplay the form with error messages
            Tura tura = turaRepo.findById(tourId).orElseThrow(() -> new RuntimeException("Tour not found"));
            model.addAttribute("tura", tura);
            return "driver/rezervacijaForma";
        }

        Tura tura = turaRepo.findById(tourId).orElseThrow(() -> new RuntimeException("Tour not found"));
        long brojDostupnihMjesta = tura.getBrojMjesta();
        long brojPopunjenihMjesta = tura.getBrojPopunjenihMjesta();

        if(brojDostupnihMjesta>brojPopunjenihMjesta){
            tura.setBrojPopunjenihMjesta(brojPopunjenihMjesta+1);

            turaRepo.save(tura);

            bookingLogs.setTura(tura);
            Korisnik korisnik=korisnikDetails.getKorisnik();
            bookingLogs.setKorisnik(korisnik);
            bookingRepo.save(bookingLogs);

            return "redirect:/driver/rezervacija";

        } else{
            model.addAttribute("errorMessage", "Nema dostupnih mjesta za rezervaciju");
            return "driver/rezervacijaForma";
        }
    }

    @GetMapping("/driver/mojeRezervacije")
    @PreAuthorize("hasAuthority('DRIVER')")
    public String listMojeRezervacije(Model model, @AuthenticationPrincipal KorisnikDetails korisnikDetails) {
        Korisnik korisnik = korisnikDetails.getKorisnik();
        List<BookingLogs> mojeRezervacije = bookingRepo.findByKorisnik(korisnik);
        System.out.println(korisnik.getIme());
        System.out.println(korisnik.getId());
        model.addAttribute("mojeRezervacije", mojeRezervacije);
        return "driver/mojeRezervacije";
    }


    @GetMapping("/driver/mojeRezervacije/edit/{id}")
    @PreAuthorize("hasAuthority('DRIVER')")
    public String editReservation(@PathVariable Long id, Model model) {
        BookingLogs rezervacija = bookingRepo.findById(id).orElseThrow(() -> new RuntimeException("Rezervacija not found"));

        // Dodajte potrebne informacije u model
        model.addAttribute("rezervacija", rezervacija);

        return "driver/editReservation"; // Kreirajte HTML stranicu za uređivanje rezervacije
    }

    @PostMapping("/driver/mojeRezervacije/edit/{id}")
    @PreAuthorize("hasAuthority('DRIVER')")
    public String updateReservation(@PathVariable Long id, @ModelAttribute @Valid BookingLogs updatedRezervacija, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Ako postoje pogreške, vratite korisnika na formu za uređivanje
            return "driver/editReservation";
        }

        BookingLogs rezervacija = bookingRepo.findById(id).orElseThrow(() -> new RuntimeException("Rezervacija not found"));

        // Ažurirajte potrebne informacije
        rezervacija.setDatumiVrijeme(updatedRezervacija.getDatumiVrijeme());
        rezervacija.setNacinDolaska(updatedRezervacija.getNacinDolaska());
        rezervacija.setTrebamMotocikl(updatedRezervacija.getTrebamMotocikl());
        rezervacija.setTrebamOpremu(updatedRezervacija.getTrebamOpremu());
        rezervacija.setPoruka(updatedRezervacija.getPoruka());

        // Spremite ažuriranu rezervaciju
        bookingRepo.save(rezervacija);

        return "redirect:/driver/mojeRezervacije";
    }

    @GetMapping("/driver/mojeRezervacije/delete/{id}")
    @PreAuthorize("hasAuthority('DRIVER')")
    public String deleteReservation(@PathVariable Long id) {
        BookingLogs rezervacija = bookingRepo.findById(id).orElseThrow(() -> new RuntimeException("Rezervacija not found"));

        Tura tura = rezervacija.getTura();
        tura.setBrojPopunjenihMjesta(tura.getBrojPopunjenihMjesta() - 1);
        turaRepo.save(tura);

        // Obrišite rezervaciju
        bookingRepo.deleteById(id);
        return "redirect:/driver/mojeRezervacije";
    }
}