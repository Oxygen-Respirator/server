package com.oxygen.oxygenspring._common.exception.utils;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.oxygen.oxygenspring._common.exception.errorCode.ResponseCode;
import com.oxygen.oxygenspring._common.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

@Slf4j
public class ExceptionUtils {


    /* 응답 객체 생성 */
    /*  - 디폴트 메세지 노출 */
    public static ResponseEntity<Object> handleExceptionInternal(ResponseCode code) {
        return ResponseEntity.status(code.getStatus()).body(ApiResponse.error(code));
    }

    /*  - 커스텀 메세지 노출 */
    public static ResponseEntity<Object> handleExceptionInternal(ResponseCode code, String message) {
        return ResponseEntity.status(code.getStatus()).body(ApiResponse.error(code, message));
    }

    /* Exception log를 찍는 함수 */
    /*  - 디폴트 메세지 노출 */
    public static void loggingException(Exception e) {
        log.error("Exception ::: [{} : {}]", getExceptionName(e), e.getMessage());

        loggingStackTraceLocation(e);
    }

    /*  - 커스텀 메세지 노출 */
    public static void loggingException(Exception e, String message) {
        log.error("Exception ::: [{} : {}]", getExceptionName(e), message);

        loggingStackTraceLocation(e);
    }

    /* StackTrace 에서 에러 발생 첫번째 위치를 출력 하는 함수 */
    public static void loggingStackTraceLocation(Exception e) {
        String prefix = "com.roomio.agoda";

        for (StackTraceElement element : e.getStackTrace()) {
            if (element.getClassName().startsWith(prefix)) {
                log.error("Location ::: [{} : {} : {}]", element.getClassName(), element.getLineNumber(), element.getMethodName());
                break;
            }
        }
    }

    /* Exception 이름을 가져 오는 함수 */
    public static String getExceptionName(Exception e) {
        String[] exceptionNameArr = e.getClass().getName().split("\\.");
        return exceptionNameArr[exceptionNameArr.length - 1];
    }

    public static String getInvalidFormatExceptionMessage(InvalidFormatException e) {
        String message = "";

        if (!e.getPath().isEmpty()) {
            JsonMappingException.Reference ref = e.getPath().get(e.getPath().size() - 1);

            message = "필드명 [" + ref.getFieldName() + "]는 [" + e.getTargetType().getSimpleName() + "]타입이어야합니다. 입력값 : (" + e.getValue().getClass().getSimpleName() + ") " + e.getValue();
        }
        return message;
    }

    public static String getMismatchedInputExceptionMessage(MismatchedInputException mismatchedInputException) {
        return "[" + mismatchedInputException.getTargetType().getSimpleName() + "] 타입인 필드에 JSON 형식의 값이 입력되었습니다.";
    }
}
