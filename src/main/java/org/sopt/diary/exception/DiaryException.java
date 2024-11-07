package org.sopt.diary.exception;

import org.sopt.diary.code.FailureCode;

public class DiaryException  extends RuntimeException{

    private final FailureCode failureCode;

    public FailureCode getFailureCode() {
        return failureCode;
    }


    public DiaryException(FailureCode failureCode) {
        //super(failureCode);
        this.failureCode = failureCode;
    }
}
