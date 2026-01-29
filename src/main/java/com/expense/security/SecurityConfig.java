package com.expense.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

import com.expense.ui.Login;
import com.vaadin.flow.spring.security.VaadinAwareSecurityContextHolderStrategyConfiguration;
import com.vaadin.flow.spring.security.VaadinSecurityConfigurer;

@Configuration
@EnableWebSecurity
@Import(VaadinAwareSecurityContextHolderStrategyConfiguration.class)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // 🔹 Primero aplica la configuración de Vaadin
        http.with(VaadinSecurityConfigurer.vaadin(), vaadin -> {
            vaadin.loginView(Login.class);
        });

        // 🔹 Luego tus reglas adicionales
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/images/**").permitAll());

        // 🔹 Configurar redirección post-login
        http.formLogin(form -> {
            form.successHandler((request, response, authentication) -> {
                response.sendRedirect("/");
            });
            form.failureHandler((request, response, exception) -> {
                response.sendRedirect("/login?error");
            });
        });

        http.headers(headers -> headers
                .addHeaderWriter(new XFrameOptionsHeaderWriter(
                        XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)));

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
