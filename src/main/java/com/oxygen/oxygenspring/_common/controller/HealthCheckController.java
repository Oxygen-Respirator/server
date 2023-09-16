package com.oxygen.oxygenspring._common.controller;

import com.oxygen.oxygenspring._common.response.ApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @RequestMapping("/")
    public ApiResponse<String> healthCheck() {
        return ApiResponse.success();
    }
}
