package com.oxygen.oxygenspring._common.web_client;

import com.oxygen.oxygenspring._common.web_client.builder.ApiWebClientBuilder;
import com.oxygen.oxygenspring._common.web_client.statics.ApiStatics;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

@Log4j2
@Component
@RequiredArgsConstructor
public class WebClientConnector {
    private final ApiWebClientBuilder webClientBuilder;
    private final ApiStatics statics;


    @SuppressWarnings("unchecked")
    public <Q, S> S callOpenApi(HttpMethod method, String path, Map<String, String> headers, MultiValueMap<String, String> params, Q requestBody, Class<S> responseType) {
        if (headers == null) headers = Map.of();
        headers.put("Authorization", "Bearer " + statics.getOpenAi().getToken());

        if (params == null) params = new LinkedMultiValueMap<>();

        return (S) webClientBuilder.request()
                .method(method, statics.getOpenAi().getUrl(), path, params, requestBody)
                .connectBlock(headers, responseType)
                .toObjectCall();
    }

}
