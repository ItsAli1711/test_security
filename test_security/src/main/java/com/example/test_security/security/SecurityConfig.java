package com.example.test_security.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@RequiredArgsConstructor                      //this helps us Constructor inject by declaring the object as private final
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception
    {
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req.requestMatchers("/home","/reg").permitAll()
                        .requestMatchers("/admin").hasAuthority("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                //.sessionManagement(ses -> ses.sessionCreationPolicy(STATELESS))     //Enable this only when jwt is implemented
                .authenticationProvider(authenticationProvider());        // we define our authorization provider for the security
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider()     //we use a DaoAuthentication Provider.
    {
        DaoAuthenticationProvider authprovider = new DaoAuthenticationProvider();
        authprovider.setPasswordEncoder(passwordEncoder());            //set password encoder type,the password field user enters,is encoded using this encoder
        authprovider.setUserDetailsService(userDetailsService);        //define out UserDetailsService
        return authprovider;
    }

    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder(12);                                   //this the encoder we will use
    }
}
