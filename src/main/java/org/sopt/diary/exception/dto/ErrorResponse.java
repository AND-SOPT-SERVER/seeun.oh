package org.sopt.diary.exception.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;


public record ErrorResponse(
        int status,
        String message,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        List<CustomFieldError> errors
) {

    //error가 null인 경우의 생성자
    public ErrorResponse(int status, String message) {
        this(status, message, null);
    }

    public static ErrorResponse of(int status, String message) {
        return new ErrorResponse(status, message);
    }

    public static ErrorResponse of(int status, String message, BindingResult bindingResult) {
        return new ErrorResponse(status, message, CustomFieldError.of(bindingResult));
    }

    public static class CustomFieldError {
        private String field;
        private String message;

        private CustomFieldError(String field, String message) {
            this.field = field;
            this.message = message;
        }
        public String getField() {
            return field;
        }
        public String getMessage() {
            return message;
        }

        private CustomFieldError(FieldError fieldError) {
            this.field = fieldError.getField();
            this.message = fieldError.getDefaultMessage();
        }

        public static List<CustomFieldError> of(final BindingResult bindingResult) {
            return bindingResult.getFieldErrors().stream().map(CustomFieldError::new).toList();
        }


    }
}
