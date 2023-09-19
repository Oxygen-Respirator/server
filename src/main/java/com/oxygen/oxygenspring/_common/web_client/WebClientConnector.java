package com.oxygen.oxygenspring._common.web_client;

import com.oxygen.oxygenspring._common.web_client.builder.ApiWebClientBuilder;
import com.oxygen.oxygenspring._common.web_client.statics.ApiStatics;
import com.oxygen.oxygenspring._common.web_client.statics.HubUrl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;

import java.util.Map;

@Log4j2
@Component
@RequiredArgsConstructor
public class WebClientConnector {
    private final ApiWebClientBuilder webClientBuilder;
    private final ApiStatics statics;
    private final HubUrl hubUrl;


    public String callSoapString(String url, String path, Map<String, String> headers, String requestBody) {
        return (String) webClientBuilder.request()
                .post(url, path, requestBody)
                .connectBlock(headers, String.class)
                .toObjectCall();
    }

    public Mono<?> callSoapStringBySubscribe(String url, String path, Map<String, String> headers, String requestBody) {
        return webClientBuilder.request()
                .post(url, path, requestBody)
                .connectSubscribe(headers, String.class);
    }

}
