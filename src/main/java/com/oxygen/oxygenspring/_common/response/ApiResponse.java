package com.oxygen.oxygenspring._common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.oxygen.oxygenspring._common.exception.responseCode.ResponseCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApiResponse<T> {
    private String code;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL) // 필드가 null 일 때 직렬화 제외
    private T data;

    public ApiResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    // 리턴할 데이터가 있을 때 성공 응답
    public static <T> ApiResponse<T> success(T data) {
        ResponseCode success = ResponseCode.SUCCESS;
        return new ApiResponse<>(success.getCode(), success.getMessage(), data);
    }

    // 리턴할 데이터가 없을 때 성공 응답
    public static <T> ApiResponse<T> success() {
        ResponseCode success = ResponseCode.SUCCESS;
        return new ApiResponse<>(success.getCode(), success.getMessage());
    }

    // 리턴할 데이터가 있을 때 에러 응답
    public static <T> ApiResponse<T> error(ResponseCode responseCode, T data) {
        return new ApiResponse<>(responseCode.getCode(), responseCode.getMessage(), data);
    }

    // 리턴할 데이터가 없을 때 에러 응답
    public static <T> ApiResponse<T> error(ResponseCode responseCode) {
        return new ApiResponse<>(responseCode.getCode(), responseCode.getMessage());
    }

}
