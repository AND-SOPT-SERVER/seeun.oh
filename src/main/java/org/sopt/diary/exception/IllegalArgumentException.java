package org.sopt.diary.exception;

import org.sopt.diary.exception.code.ErrorCode;

public class IllegalArgumentException extends RuntimeException {
    private final ErrorCode errorCode;

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public IllegalArgumentException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
