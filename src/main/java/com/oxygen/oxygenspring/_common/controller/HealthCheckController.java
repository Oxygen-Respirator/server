package com.oxygen.oxygenspring._common.controller;

import com.oxygen.oxygenspring._common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "00. Healthcheck", description = "헬스 체크")
public class HealthCheckController {

    @GetMapping("/")
    @Operation(summary = "Health check")
    public ApiResponse<String> healthCheck() {
        return ApiResponse.success();
    }
}
