package org.sopt.diary.exception.code;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    INVALID_VALUES(HttpStatus.BAD_REQUEST, "잘못된 값입니다."),
    NOT_EXISTS_CATEGORY(HttpStatus.BAD_REQUEST, "유효하지 않은 카테고리입니다."),
    //user
    NOT_EXISTS_WITH_USERNAME(HttpStatus.NOT_FOUND, "존재하지 않는 username입니다"),
    //NOT_EXISTS_WITH_USERID(HttpStatus.NOT_FOUND, "해당 id의 유저가 존재하지 않습니다"),
    WRONG_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    ALREADY_EXISTS_USERNAME(HttpStatus.CONFLICT, "이미 존재하는 username입니다."),

    //diary
    NOT_EXISTS_WITH_ID(HttpStatus.NOT_FOUND, "존재하지 않는 일기 id입니다."),

    //user & diary
    NOT_EXISTS_WITH_USERNAME_AND_ID(HttpStatus.NOT_FOUND, "해당 username에 일치하는 id의 일기가 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
