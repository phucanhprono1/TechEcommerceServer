package com.example.techecommerceserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
public class CorsConfig {

    @Bean
    CorsWebFilter corsFilter() {

        CorsConfiguration config = new CorsConfiguration();

        // Add the required headers, including 'ngrok-skip-browser-warning'
        config.addAllowedOrigin("http://127.0.0.1:5502");
        config.addAllowedHeader("*");
        config.addAllowedHeader("ngrok-skip-browser-warning"); // Add this line
        config.addAllowedMethod("*");
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }
}
