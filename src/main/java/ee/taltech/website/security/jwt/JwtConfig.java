package ee.taltech.website.security.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig { //external config for jwt

    private String secret = "trivago";
    private int durationMin = 10;

    public int getDurationMillis() {
        return durationMin * 60 * 1000;
    }
}
