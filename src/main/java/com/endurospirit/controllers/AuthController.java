package com.endurospirit.controllers;

import com.endurospirit.models.Korisnik;
import com.endurospirit.repositories.KorisnikRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {
    @Autowired
    KorisnikRepository korisnikRepo;

    @GetMapping("/auth/register")
    public String register (Model model){
        Korisnik korisnik = new Korisnik();
        model.addAttribute("korisnik", korisnik);
        return "users/register";
    }

    @PostMapping("/auth/register")
    public String newKorisnik(@Valid Korisnik korisnik, BindingResult result, Model model){
        boolean errors=result.hasErrors();
        if(errors) {
            model.addAttribute("korisnik", korisnik);
            return "users/register";
        }else{
            BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
            String passwordEncoded=encoder.encode(korisnik.getLozinka());
            korisnik.setLozinka(passwordEncoded);
            korisnik.setPotvrdaLozinke(passwordEncoded);
            korisnikRepo.save(korisnik);
            return "redirect:/auth/register";
        }
    }
}
