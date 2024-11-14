package org.sopt.diary.exception;

import org.sopt.diary.exception.code.ErrorCode;

public class NotFoundException extends RuntimeException {
    private final ErrorCode errorCode;

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public NotFoundException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
