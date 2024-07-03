package dodo.reddit.jwt;

import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
@RequiredArgsConstructor
public class JwtSecretKey {
    private final JwtConfig config;

    @Bean
    public SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(config.getSecretKey().getBytes());
    }
}
