package com.oxygen.oxygenspring.domain.user.jwt.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oxygen.oxygenspring._common.exception.responseCode.ResponseCode;
import com.oxygen.oxygenspring._common.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtTokenAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException {
        setResponse(response);
    }

    private void setResponse(HttpServletResponse response) throws IOException {
        ObjectMapper om = new ObjectMapper();

        ResponseCode responseCode = ResponseCode.ACCESS_DENIED;

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(responseCode.getStatus().value());

        response.getWriter().print(om.writeValueAsString(ApiResponse.error(responseCode)));
    }
}
