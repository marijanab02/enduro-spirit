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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TuraController {
    @Autowired
    private TuraRepository turaRepo;

    @Autowired
    private BookingRepository bookingRepo;
    @GetMapping("/supervisor/PodaciOTuri")
    public String addTura (Model model){
        Tura tura = new Tura();
        model.addAttribute("tura", tura);
        return "supervisor/PodaciOTuri";
    }

    @PostMapping("/supervisor/PodaciOTuri")
    public String newTura (@Valid Tura tura, BindingResult result, Model model){
        boolean errors=result.hasErrors();
        if(errors) {
            model.addAttribute("tura", tura);
            return "supervisor/PodaciOTuri";
        }else {
            tura.setBrojPopunjenihMjesta(0L);
            turaRepo.save(tura);
            return "redirect:/supervisor/listTura";
        }
    }
    @GetMapping("/driver/rezervacija")
    @PreAuthorize("hasAuthority('DRIVER')")
    public String listTura (Model model){
        List<Tura> ture = turaRepo.findByBrojMjestaGreaterThan();
        model.addAttribute("ture", ture);

        model.addAttribute("bookingLogs", new BookingLogs());
        return "driver/rezervacija";
    }
}