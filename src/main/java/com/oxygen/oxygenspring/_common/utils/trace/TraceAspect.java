package com.oxygen.oxygenspring._common.utils.trace;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.oxygen.oxygenspring._common.exception.ApiException;
import com.oxygen.oxygenspring._common.exception.responseCode.ResponseCode;
import com.oxygen.oxygenspring._common.response.ApiResponse;
import com.oxygen.oxygenspring._common.utils.IpUtils;
import com.oxygen.oxygenspring.db.entity.ApiLog;
import com.oxygen.oxygenspring.db.repository.ApiLogRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Aspect
@Component
@RequiredArgsConstructor
@Log4j2
public class TraceAspect {

    private final ApiLogRepository apiLogRepository;
    private final ObjectMapper om;

    @Around("execution(* com.oxygen.oxygenspring.domain..controller..*(..))")
//    @Transactional
    public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {

        // Method 명 가져 오기
        String method = joinPoint.getSignature().getName();

        // IP 가져오기
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String requestIp = IpUtils.getClientIp(request);

        // RequestBody 가져 오기
        JsonNode requestBody = new ObjectNode(JsonNodeFactory.instance);
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            requestBody = om.convertValue(joinPoint.getArgs()[0], JsonNode.class);
        }


        Object result;
        String status;

        Throwable finalException = null;

        try {
            /* 메인 로직 실행 */
            status = "S";
            result = joinPoint.proceed();

        } catch (Throwable e) {
            finalException = e;
            status = "E";

            if (e instanceof ApiException exception) {
                JsonNode errorResult = om.convertValue(exception, JsonNode.class);

                ResponseCode responseCode;
                String errorMessage;
                if (errorResult.has("code") && errorResult.has("message")) {
                    responseCode = ResponseCode.valueOf(errorResult.get("code").asText());
                    errorMessage = errorResult.get("message").asText();
                } else {
                    responseCode = ResponseCode.INTERNAL_SERVER_ERROR;
                    errorMessage = "알 수 없는 에러";
                }

                result = ApiResponse.error(responseCode, errorMessage);
            } else {
                result = ApiResponse.error(ResponseCode.INTERNAL_SERVER_ERROR, "알 수 없는 에러");
            }
        }

        JsonNode responseBody = om.convertValue(result, JsonNode.class);


        ApiLog build = ApiLog.builder()
                .status(status)
                .method(method)
                .req(requestBody.toPrettyString())
                .res(responseBody.toPrettyString())
                .ip(requestIp)
                .build();
        ApiLog savedLog = apiLogRepository.save(build);

        try {
            log.info(om.writeValueAsString(savedLog));
        } catch (JsonProcessingException e) {
            throw new ApiException(ResponseCode.INTERNAL_SERVER_ERROR, "Json 변환 실패");
        }


        // 예외가 발생했다면 다시 던지기
        if (StringUtils.equals(status, "E") && finalException != null) {
            throw finalException;
        }


        return result;
    }

}
