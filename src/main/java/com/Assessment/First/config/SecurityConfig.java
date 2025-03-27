package com.Assessment.First.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationProvider authProvider()
    {
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                        http
                                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                        .csrf(csrf -> csrf.disable())
                        .authorizeHttpRequests(request->request
                        .requestMatchers("/users/register","/users/login","/flowable-rest/**")
                        .permitAll()
                        .anyRequest().authenticated()).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);


                        return http.build();
    }



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


//    @Bean
//    public UserDetailsService userDetailsService()
//    {
//        UserDetails user= User
//                          .withDefaultPasswordEncoder()
//                          .username("jagga")
//                          .password("1234")
//                          .roles("USER")
//                          .build();
//        return new InMemoryUserDetailsManager(user);
//    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(List.of("http://localhost:4200"));
        corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        corsConfig.setAllowedHeaders(List.of("*"));
        corsConfig.setExposedHeaders(List.of("Authorization"));
        corsConfig.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);
        return source;
    }














}




