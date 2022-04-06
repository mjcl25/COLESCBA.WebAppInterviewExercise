package ar.com.mjcl.WebAppInterviewExercise.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IntegrationConfiguration {

    @Value("${integration.url}")
    private String integrationUrl;

    @Value("${integration.issuer}")
    private String tokenIssuer;

    @Value("${integration.secret}")
    private String tokenSecret;

    public String getIntegrationUrl() {
        return integrationUrl;
    }

    public String getTokenIssuer() {
        return tokenIssuer;
    }

    public String getTokenSecret() {
        return tokenSecret;
    }

}
