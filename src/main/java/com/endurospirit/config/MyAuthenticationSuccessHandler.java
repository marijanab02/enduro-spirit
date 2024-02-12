package com.endurospirit.config;

import com.endurospirit.models.KorisnikDetails;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        var authorities=authentication.getAuthorities();
        System.out.println("Authorities: " + authorities);
        if(authorities.stream().anyMatch(a ->a.getAuthority().equals("ADMIN"))){
            response.sendRedirect("/users");
        }else if(authorities.stream().anyMatch(a -> a.getAuthority().equals("SUPERVISOR"))){
            response.sendRedirect("/supervisor/listTura");
        }else if(authorities.stream().anyMatch(a -> a.getAuthority().equals("DRIVER"))){
            if (authentication.getPrincipal() instanceof KorisnikDetails) {
                KorisnikDetails korisnikDetails = (KorisnikDetails) authentication.getPrincipal();
                // Dodaj korisnika u sesiju
                request.getSession().setAttribute("korisnikDetails", korisnikDetails);
            }
            response.sendRedirect("/driver/rezervacija");
        }else{
            response.sendRedirect("/auth/home");
        }

    }
}