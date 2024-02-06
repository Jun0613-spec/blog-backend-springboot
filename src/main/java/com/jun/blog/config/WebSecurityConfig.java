package com.jun.blog.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.jun.blog.jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig{

   private final JwtAuthenticationFilter jwtAuthenticationFilter;

   @Bean
   protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
    http
    .cors(cors -> cors.configurationSource(corsConfigurationSource()))
    .csrf(csrf -> csrf.disable())
    .httpBasic(httpBasic -> httpBasic.disable())
    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    .authorizeHttpRequests(request -> request
            .requestMatchers("/","/api/v1/auth/**", "/api/v1/search/**","/file/**").permitAll()
            .requestMatchers(HttpMethod.GET,"/api/v1/post/**", "/api/v1/user/**", "/api/v1/list/**").permitAll()
            .anyRequest().authenticated())
            .addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter.class);

   return http.build();

   }

    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {

    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of("/**","http://localhost:3000", "https://blogfy0613.onrender.com","https://blog-frontend-one-hazel.vercel.app"));
    configuration.setAllowedMethods(List.of("GET","POST","DELETE","PATCH","OPTION","PUT"));
    configuration.setAllowedHeaders(List.of("Authorization","Content-Type"));
    configuration.setAllowCredentials(true);

    
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);

    return source;
    }
}


