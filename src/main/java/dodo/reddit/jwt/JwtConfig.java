package dodo.reddit.jwt;

import com.google.common.net.HttpHeaders;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Scope;

@ConfigurationProperties(prefix = "application.jwt")
@Getter
public class JwtConfig{

    private final String secretKey;
    private final String tokenPrefix;
    private final Integer expirationAfterDays;


    public JwtConfig(String secretKey, String tokenPrefix, Integer expirationAfterDays) {
        this.secretKey = secretKey;
        this.tokenPrefix = tokenPrefix + " ";
        this.expirationAfterDays = expirationAfterDays;
    }

    public String getHeader(){
        return HttpHeaders.AUTHORIZATION;
    }
}