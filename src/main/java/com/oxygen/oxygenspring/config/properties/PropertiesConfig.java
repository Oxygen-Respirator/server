package com.oxygen.oxygenspring.config.properties;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PropertiesConfig {
    public static final List<String> ACTIVE_PROFILE_LIST = new ArrayList<>();

    private final Environment env;

    @PostConstruct
    public void init() {
        ACTIVE_PROFILE_LIST.addAll(List.of(env.getActiveProfiles()));
        ACTIVE_PROFILE_LIST.addAll(List.of(env.getDefaultProfiles()));
    }
}
