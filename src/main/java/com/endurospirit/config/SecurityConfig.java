package com.endurospirit.config;

import com.endurospirit.services.KorisnikDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class SecurityConfig {
    @Autowired
    DataSource dataSource;

    @Bean
    public KorisnikDetailsService korisnikDetailsService(){
        return new KorisnikDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();

        daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder());

        daoAuthenticationProvider.setUserDetailsService(this.korisnikDetailsService());
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new MyAuthenticationSuccessHandler();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests()
                .requestMatchers("/auth/register/**","/auth/register", "/WebLogo.png", "/auth/home", "/enduro1.jpg")
                .permitAll()
                .requestMatchers("/users/**").hasAuthority("ADMIN")
                .requestMatchers("/supervisor/**").hasAuthority("SUPERVISOR")
                .requestMatchers("/driver/**").hasAuthority("DRIVER")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().successHandler(myAuthenticationSuccessHandler())
                .loginPage("/auth/login")
                .permitAll()
                .usernameParameter("email")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/").permitAll();

        http.authenticationProvider(authenticationProvider());
        http.headers().frameOptions().sameOrigin();

        http.exceptionHandling().authenticationEntryPoint((request, response, authException) ->
                response.sendRedirect("/auth/home"));
        return http.build();
    }
}
