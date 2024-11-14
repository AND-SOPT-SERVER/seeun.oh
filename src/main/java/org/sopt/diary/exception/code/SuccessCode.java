package org.sopt.diary.exception.code;

import org.springframework.http.HttpStatus;

public enum SuccessCode {
    SUCCESS_CREATE_DIARY(HttpStatus.CREATED, "일기 작성에 성공했습니다."),
    SUCCESS_UPDATE_DIARY(HttpStatus.OK, "일기 수정에 성공했습니다."),
    SUCCESS_DELETE_DIARY(HttpStatus.OK, "일기 삭제에 성공했습니다."),
    SUCCESS_GET_DIARY_DETAIL(HttpStatus.OK, "일기 상세 조회에 성공했습니다.");


    private final HttpStatus httpStatus;
    private final String message;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
    SuccessCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
