package com.oxygen.oxygenspring._common.exception.responseCode;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseCode {

    /* 200 */
    SUCCESS(HttpStatus.OK, "200_0", "정상 처리 되었습니다."),

    /* 400 */
    VALIDATION_FAILED(HttpStatus.BAD_REQUEST, "400_0", "유효성 검사에 실패했습니다."),
    INVALID_REQUEST_BODY(HttpStatus.BAD_REQUEST, "400_1", "Request body가 잘못되었습니다."),
    INVALID_REQUEST_METHOD_TYPE(HttpStatus.BAD_REQUEST, "400_2", "Request Method가 잘못되었습니다."),

    /* 401 */
    WRONG_TYPE_SIGNATURE(HttpStatus.UNAUTHORIZED, "401_0", "잘못된 JWT 서명입니다."),
    WRONG_TYPE_TOKEN(HttpStatus.UNAUTHORIZED, "401_1", "유효하지 않은 구성의 JWT 토큰입니다."),
    EXPIRED_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "401_2", "만료된 JWT 토큰입니다."),
    INVALID_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "401_3", "지원되지 않는 형식이나 구성의 JWT 토큰입니다."),
    ACCESS_TOKEN_NOT_EXIST(HttpStatus.UNAUTHORIZED, "401_4", "Access Token이 존재하지 않습니다."),
    ACCESS_DENIED(HttpStatus.UNAUTHORIZED, "401_5", "접근이 거부되었습니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "401_6", "비밀번호가 일치하지 않습니다."),

    /* 404 */
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "404_0", "데이터가 존재하지 않습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "404_1", "해당 유저가 존재하지 않습니다."),

    /* 409 */
    EXIST_USER(HttpStatus.CONFLICT, "409_0", "이미 존재하는 유저입니다."),

    /* 500 */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500_0", "서버에 문제가 발생했습니다. 관리자에게 문의해주세요."),
    GOOGLE_CHAT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500_1", "Google chat 메시지 전송에 실패하였습니다."),
    KAFKA_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500_2", "Kafka 통신에 실패하였습니다."),
    TOKEN_VALID_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "500_9", "토큰 생성에 실패하였습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    ResponseCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
