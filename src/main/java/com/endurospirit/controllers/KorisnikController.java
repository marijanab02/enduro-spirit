package com.endurospirit.controllers;

import com.endurospirit.models.Korisnik;
import com.endurospirit.repositories.KorisnikRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class KorisnikController {

    @Autowired
    KorisnikRepository korisnikRepository;

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String listUsers (Model model){
        List<Korisnik> users = korisnikRepository.findAll();
        model.addAttribute("users", users);
        return "users/index";
    }

    @GetMapping("/users/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String showAddUserForm(Model model) {
        model.addAttribute("korisnik", new Korisnik());
        return "users/add";
    }

    @PostMapping("/users/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addUser(@Valid Korisnik korisnik, BindingResult result, Model model) {
        if (result.hasErrors()){
            model.addAttribute("korisnik", korisnik);
            return "users/add";
        } else {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String passwordEncoded = encoder.encode(korisnik.getLozinka());
            korisnik.setLozinka(passwordEncoded);
            korisnik.setPotvrdaLozinke(passwordEncoded);
            korisnikRepository.save(korisnik);
            return "redirect:/users";
        }
    }

    @PostMapping("/users/delete/{korisnikId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteUser(@PathVariable Long korisnikId) {
        korisnikRepository.deleteById(korisnikId);
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{korisnikId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String showEditUserForm(@PathVariable Long korisnikId, Model model) {
        Korisnik korisnik = korisnikRepository.findById(korisnikId)
                .orElseThrow(() -> new IllegalArgumentException("Neispravan ID korisnika: " + korisnikId));
        model.addAttribute("korisnik", korisnik);
        return "users/edit";
    }

    @PostMapping("/users/edit/{korisnikId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updateUser(@PathVariable Long korisnikId, @ModelAttribute Korisnik korisnik, Model model) {
        Korisnik existingUser = korisnikRepository.findById(korisnikId)
                .orElseThrow(() -> new IllegalArgumentException("Neispravan ID korisnika: " + korisnikId));
        existingUser.setIme(korisnik.getIme());
        existingUser.setPrezime(korisnik.getPrezime());
        existingUser.setEmail(korisnik.getEmail());
        existingUser.setAdresa(korisnik.getAdresa());
        existingUser.setTelefon(korisnik.getTelefon());
        existingUser.setDatumRodenja(korisnik.getDatumRodenja());
        existingUser.setTezina(korisnik.getTezina());
        existingUser.setVisina(korisnik.getVisina());
        existingUser.setVelicinaKacige(korisnik.getVelicinaKacige());
        existingUser.setVelicinaObuce(korisnik.getVelicinaObuce());
        existingUser.setVelicinaOdjece(korisnik.getVelicinaOdjece());
        existingUser.setVozackeSposobnosti(korisnik.getVozackeSposobnosti());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String lozinka = encoder.encode(korisnik.getLozinka());
        existingUser.setLozinka(lozinka);
        existingUser.setPotvrdaLozinke(lozinka);
        existingUser.setRoles(korisnik.getRoles());

        korisnikRepository.save(existingUser);
        return "redirect:/users";
    }

    @GetMapping("/supervisor/viewReservations/{userId}")
    @PreAuthorize("hasAuthority('SUPERVISOR')")
    public String viewUserDetails(@PathVariable Long userId, Model model) {
        Korisnik korisnik = korisnikRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        model.addAttribute("korisnik", korisnik);
        return "/supervisor/viewUserDetails";
    }
}