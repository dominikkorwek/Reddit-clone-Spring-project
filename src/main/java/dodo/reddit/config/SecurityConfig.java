package dodo.reddit.config;

import dodo.reddit.jwt.JwtConfig;
import dodo.reddit.jwt.JwtTokenVerifier;
import dodo.reddit.jwt.JwtUsernameAndPasswordFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableJpaRepositories(basePackages = "dodo.reddit.repositories")
public class SecurityConfig{
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService appUserDetailsService;

    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(appUserDetailsService);
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
                                                   AuthenticationManager manager,
                                                   JwtConfig config, SecretKey secretKey) throws Exception {
        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated())
                .addFilter(new JwtUsernameAndPasswordFilter(manager,config,secretKey))
                .addFilterBefore(new JwtTokenVerifier(config,secretKey),JwtUsernameAndPasswordFilter.class)
                .authenticationManager(manager)
                .authenticationProvider(daoAuthenticationProvider())
                .build();
    }
}
