package com.endurospirit.controllers;

import com.endurospirit.models.Korisnik;
import com.endurospirit.repositories.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    @Autowired
    KorisnikRepository korisnikRepo;

    @GetMapping("/users/register")
    public String register (Model model){
        Korisnik korisnik = new Korisnik();
        model.addAttribute("korisnik", korisnik);
        return "users/register";
    }

    @PostMapping("/users/register")
    public String newKorisnik(Korisnik korisnik, BindingResult result, Model model){
        korisnikRepo.save(korisnik);
        return "redirect:/users/register";
    }
}
