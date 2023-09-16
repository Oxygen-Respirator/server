package com.oxygen.oxygenspring._common.exception.errorCode;

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

    /* 404 */
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "404_0", "데이터가 존재하지 않습니다."),

    /* 500 */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500_0", "서버에 문제가 발생했습니다. 관리자에게 문의해주세요."),
    GOOGLE_CHAT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500_1", "Google chat 메시지 전송에 실패하였습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

    ResponseCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
