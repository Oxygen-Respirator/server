package com.oxygen.oxygenspring.domain.user.jwt.entry_point;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oxygen.oxygenspring._common.exception.responseCode.ResponseCode;
import com.oxygen.oxygenspring._common.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

@Log4j2
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {
        String exception = (String) request.getAttribute("exception");

        if (exception == null) {
            setResponse(response, ResponseCode.ACCESS_DENIED);
        }

        //잘못된 타입의 토큰인 경우
        else if (exception.equals(ResponseCode.WRONG_TYPE_TOKEN.getCode())) {
            setResponse(response, ResponseCode.WRONG_TYPE_TOKEN);
        } else if (exception.equals(ResponseCode.WRONG_TYPE_SIGNATURE.getCode())) {
            setResponse(response, ResponseCode.WRONG_TYPE_SIGNATURE);
        }
        //토큰 만료된 경우
        else if (exception.equals(ResponseCode.EXPIRED_ACCESS_TOKEN.getCode())) {
            setResponse(response, ResponseCode.EXPIRED_ACCESS_TOKEN);
        }
        // 토큰이 존재 하지 않을 경우
        else if (exception.equals(ResponseCode.ACCESS_TOKEN_NOT_EXIST.getCode())) {
            setResponse(response, ResponseCode.ACCESS_TOKEN_NOT_EXIST);
        } else {
            setResponse(response, ResponseCode.INVALID_ACCESS_TOKEN);
        }
    }

    //한글 출력을 위해 getWriter() 사용
    private void setResponse(HttpServletResponse response, ResponseCode responseCode) throws IOException {
        ObjectMapper om = new ObjectMapper();

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(responseCode.getStatus().value());
        response.getWriter().print(om.writeValueAsString(ApiResponse.error(responseCode)));
    }
}
