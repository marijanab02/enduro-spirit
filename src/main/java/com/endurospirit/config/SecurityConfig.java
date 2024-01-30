package com.endurospirit.config;

import com.endurospirit.services.KorisnikDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
    @Autowired
    DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService(){
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

        daoAuthenticationProvider.setUserDetailsService((this.userDetailsService()));
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests()
                .requestMatchers("/auth/register/**","/auth/register")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/login")
                .permitAll()
                .usernameParameter("email")
                .defaultSuccessUrl("/users", true)
                .permitAll()
                .and()
                .logout().logoutSuccessUrl("/").permitAll();


        http.headers().frameOptions().sameOrigin();

        return http.build();
    }
}
