package com.oxygen.oxygenspring._common.web_client.statics;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "hub")
@Getter
@Setter
public class HubUrl {
    private Backend backend;

    @Getter
    @Setter
    public static class Backend {
        private String url;
    }
}
