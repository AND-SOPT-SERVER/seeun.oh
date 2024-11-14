package org.sopt.diary.exception.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.sopt.diary.exception.code.SuccessCode;


public record SuccessResponse<T>(
        int status,
        String message,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        T data
)
{
    public static <T> SuccessResponse<T> of(SuccessCode successCode, T data) {
        return new SuccessResponse<>(successCode.getHttpStatus().value(), successCode.getMessage(), data);
    }

    //응답에 data 없을 때
    public static SuccessResponse<Void> of(SuccessCode successCode) {
        return new SuccessResponse<>(successCode.getHttpStatus().value(), successCode.getMessage(), null);
    }

}
