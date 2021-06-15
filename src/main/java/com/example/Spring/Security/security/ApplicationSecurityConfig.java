package com.example.Spring.Security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.Spring.Security.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    public final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasRole(CLIENT.name())
                .antMatchers("/api/v1/cars/**").hasRole(ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails dariusUser = User.builder()
                .username("Darius")
                .password(passwordEncoder.encode("password"))
                .roles(CLIENT.name()) // ROLE_CLIENT
                .build();
        UserDetails lexUser = User.builder()
                .username("Lex")
                .password(passwordEncoder.encode("password123"))
                .roles(ADMIN.name()) // ROLE_ADMIN
                .build();
        UserDetails mariaUser = User.builder()
                .username("Maria")
                .password(passwordEncoder.encode("password123"))
                .roles(ADMINTRAINEE.name()) // ROLE_ADMINTRAINEE
                .build();
        return new InMemoryUserDetailsManager(
                dariusUser,
                lexUser,
                mariaUser
        );
    }
}
