package com.endurospirit.controllers;

import com.endurospirit.models.BookingLogs;
import com.endurospirit.models.Tura;
import com.endurospirit.repositories.BookingRepository;
import com.endurospirit.repositories.TuraRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SupervisorController {

    @Autowired
    TuraRepository turaRepo;
    @Autowired
    BookingRepository bookingRepo;
    @GetMapping("/supervisor/listTura")
    @PreAuthorize("hasAuthority('SUPERVISOR')")
    public String listTuraForSupervisor(Model model) {
        List<Tura> ture = turaRepo.findAll();
        model.addAttribute("ture", ture);

        return "supervisor/listTura";
    }

    @GetMapping("/supervisor/listTura/edit/{turaId}")
    @PreAuthorize("hasAuthority('SUPERVISOR')")
    public String editTura(@PathVariable Long turaId, Model model) {
        Tura tura = turaRepo.findById(turaId).orElseThrow(() -> new RuntimeException("Tour not found"));
        model.addAttribute("tura", tura);
        return "supervisor/editTura";
    }

    @PostMapping("/supervisor/listTura/edit/{turaId}")
    @PreAuthorize("hasAuthority('SUPERVISOR')")
    public String updateTura(@PathVariable Long turaId,@ModelAttribute @Valid Tura updatedTura, BindingResult result, Model model) {
        if (result.hasErrors()) {

            return "supervisor/editTura"; // Prilagodite naziv stranice
        }

        Tura existingTura = turaRepo.findById(turaId).orElseThrow(() -> new RuntimeException("Tour not found"));

        existingTura.setBrojMjesta(updatedTura.getBrojMjesta());
        existingTura.setZavrsetakTure(updatedTura.getZavrsetakTure());
        existingTura.setPocetakTure(updatedTura.getPocetakTure());
        existingTura.setTrajanjeTure(updatedTura.getTrajanjeTure());


        turaRepo.save(existingTura);

        return "redirect:/supervisor/listTura";
    }

    @GetMapping("/supervisor/listTura/delete/{turaId}")
    @PreAuthorize("hasAuthority('SUPERVISOR')")
    public String deleteTura(@PathVariable Long turaId) {
        Tura tura = turaRepo.findById(turaId).orElseThrow(() -> new RuntimeException("Tour not found"));

        turaRepo.deleteById(turaId);
        return "redirect:/supervisor/listTura";
    }

    @GetMapping("/supervisor/listTura/reservations/{turaId}")
    @PreAuthorize("hasAuthority('SUPERVISOR')")
    public String viewReservations(@PathVariable Long turaId, Model model) {
        Tura tura = turaRepo.findById(turaId).orElseThrow(() -> new RuntimeException("Tour not found"));
        List<BookingLogs> reservations = bookingRepo.findByTura(tura);
        model.addAttribute("tura", tura);
        model.addAttribute("reservations", reservations);
        return "supervisor/viewReservations"; // Prilagodite naziv stranice
    }
}