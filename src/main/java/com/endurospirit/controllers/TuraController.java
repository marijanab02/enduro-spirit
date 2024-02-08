package com.endurospirit.controllers;

import com.endurospirit.models.Korisnik;
import com.endurospirit.models.Tura;
import com.endurospirit.repositories.TuraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TuraController {
    @Autowired
    private TuraRepository turaRepo;

    @GetMapping("/supervisor/PodaciOTuri")
    public String addTura (Model model){
        Tura tura = new Tura();
        model.addAttribute("tura", tura);
        return "/supervisor/PodaciOTuri";
    }

    @PostMapping("/supervisor/PodaciOTuri")
    public String newTura (Tura tura, BindingResult result, Model model){
        turaRepo.save(tura);
        return "redirect:/supervisor/PodaciOTuri";
    }
    @GetMapping("/driver/rezervacija")
    @PreAuthorize("hasAuthority('DRIVER')")
    public String listTura (Model model){
        List<Tura> ture = turaRepo.findAll();
        model.addAttribute("ture", ture);
        return "driver/rezervacija";
    }

}