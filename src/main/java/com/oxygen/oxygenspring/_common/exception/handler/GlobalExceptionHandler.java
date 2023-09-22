package com.oxygen.oxygenspring._common.exception.handler;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.oxygen.oxygenspring._common.exception.ApiException;
import com.oxygen.oxygenspring._common.exception.responseCode.ResponseCode;
import com.oxygen.oxygenspring._common.exception.utils.ExceptionUtils;
import com.oxygen.oxygenspring.config.properties.PropertiesConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Objects;


@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    /**
     * Custom Exception
     */
    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<Object> handleApiException(ApiException e) {
        ExceptionUtils.loggingException(e);
        if (PropertiesConfig.ACTIVE_PROFILE_LIST.contains("dev")) e.printStackTrace();
        return ExceptionUtils.handleExceptionInternal(e.getCode(), e.getCode().getMessage());
    }

    /**
     * NullPointerException
     */
    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerException(NullPointerException e) {
        log.error("ᕙ༼◕ ᴥ ◕༽ᕗ 야생의 Null이 나타났다! ᕙ༼◕ ᴥ ◕༽ᕗ ");
        ExceptionUtils.loggingException(e);
        if (PropertiesConfig.ACTIVE_PROFILE_LIST.contains("dev")) e.printStackTrace();
        return ExceptionUtils.handleExceptionInternal(ResponseCode.INTERNAL_SERVER_ERROR, "비어있는 값이 들어올 수 없습니다.");
    }

    /**
     * MethodArgumentNotValidException
     * - Request의 Http method가 잘못되었을 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        String message = "[" + e.getMethod() + "] 메소드는 지원하지 않습니다. " + e.getSupportedHttpMethods() + " 메소드를 사용해주세요.";
        if (PropertiesConfig.ACTIVE_PROFILE_LIST.contains("dev")) e.printStackTrace();
        return ExceptionUtils.handleExceptionInternal(ResponseCode.INVALID_REQUEST_METHOD_TYPE, message);
    }

    /**
     * MethodArgumentTypeMismatchException
     * - URL의 parameter type이 잘못되었을 경우 발생
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        String message = "필드명 [" + e.getName() + "]은 [" + Objects.requireNonNull(e.getRequiredType()).getSimpleName() + "]타입입니다. 입력값 : (" + Objects.requireNonNull(e.getValue()).getClass().getSimpleName() + ") " + e.getValue();
        if (PropertiesConfig.ACTIVE_PROFILE_LIST.contains("dev")) e.printStackTrace();
        return ExceptionUtils.handleExceptionInternal(ResponseCode.INVALID_REQUEST_METHOD_TYPE, message);
    }

    /**
     * HttpMessageNotReadableException
     * - InvalidFormatException : RequestBody의 field type이 잘못되었을 경우 발생
     * -
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        if (PropertiesConfig.ACTIVE_PROFILE_LIST.contains("dev")) e.printStackTrace();
        String message;
        Throwable throwable = e.getMostSpecificCause();
        ResponseCode responseCode = ResponseCode.INVALID_REQUEST_BODY;

        if (throwable instanceof InvalidFormatException) {
            message = ExceptionUtils.getInvalidFormatExceptionMessage((InvalidFormatException) throwable);
        } else if (throwable instanceof MismatchedInputException) {
            message = ExceptionUtils.getMismatchedInputExceptionMessage((MismatchedInputException) throwable);
        } else if (throwable instanceof JsonParseException) {
            message = "요청 Request Body의 Json 형태가 올바르지 않습니다.";
        } else if (throwable instanceof HttpMessageNotReadableException) {
            message = "RequestBody가 비어있습니다.";
        } else {
            message = responseCode.getMessage();
        }

        ExceptionUtils.loggingException(e);

        return ExceptionUtils.handleExceptionInternal(responseCode, message);
    }

    /**
     * Validation Exception
     * - RequestBody 에 @Valid 어노테이션이 붙은 요청에 대한 유효성검사 실패 시 발생
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String defaultMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        ExceptionUtils.loggingException(e, defaultMessage);
        if (PropertiesConfig.ACTIVE_PROFILE_LIST.contains("dev")) e.printStackTrace();
        return ExceptionUtils.handleExceptionInternal(ResponseCode.VALIDATION_FAILED, defaultMessage);
    }

    /**
     * All Exception
     * - 위에서 처리 하지 않은 모든 Exception
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        ExceptionUtils.loggingException(e);
        if (PropertiesConfig.ACTIVE_PROFILE_LIST.contains("dev")) e.printStackTrace();

        return ExceptionUtils.handleExceptionInternal(ResponseCode.INTERNAL_SERVER_ERROR);
    }
}
