package com.oxygen.oxygenspring._common.web_client.builder.step;


import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;

public interface MethodStep<T> {
    ConnectStep method(String baseUrl, String path, T requestBody);

    ConnectStep method(String baseUrl, MultiValueMap<String, String> params, T requestBody);

    ConnectStep get(String baseUrl, MultiValueMap<String, String> params);

    ConnectStep get(String baseUrl, String path, MultiValueMap<String, String> params);

    ConnectStep method(HttpMethod method, String baseUrl, String path, MultiValueMap<String, String> params, T requestBody);
}
