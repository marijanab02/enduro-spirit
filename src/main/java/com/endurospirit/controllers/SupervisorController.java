package com.endurospirit.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/supervisor")
@PreAuthorize("hasAuthority('SUPERVISOR')")
public class SupervisorController {

    @GetMapping
    public String supervisorDashboard(Model model){

        return "supervisor/PodaciOTuri";
    }
}
