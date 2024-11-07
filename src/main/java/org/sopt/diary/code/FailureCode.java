package org.sopt.diary.code;

import org.springframework.http.HttpStatus;

public enum FailureCode {
    NOT_EXISTS_CATEGORY(HttpStatus.BAD_REQUEST, "잘못된 카테고리 값입니다.");

    private HttpStatus httpStatus;
    private String message;

    FailureCode(HttpStatus httpStatus, String s) {
        this.httpStatus = httpStatus;
        this.message = s;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
