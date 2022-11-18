package com.webapp.endavacourseproject;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("averageSpringFan")
                .password("loveSpringFlowers123")
                .roles("USER")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("averageSpringEnjoyer")
                .password("loveSpringBoot321")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(auth -> {
                    auth.antMatchers("/users/", "/users/add", "/mentors/").hasRole("USER");
                    auth.antMatchers("/users/", "/mentors/", "/users/add", "/users/update/{id}", "users/delete/{id}",
                            "/mentors/add", "/mentors/update/{id}", "mentors/delete/{id}").hasRole("ADMIN");
                })
                .httpBasic(withDefaults())
                .build();
    }
}
