package com.oxygen.oxygenspring._common.web_client.statics;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "keys")
@Getter
@Setter
public class ApiStatics {
    private OpenAi openAi;

    @Getter
    @Setter
    public static class OpenAi {
        private String url;
        private String token;
    }
}
