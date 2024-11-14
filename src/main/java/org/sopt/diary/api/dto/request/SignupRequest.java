package org.sopt.diary.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record SignupRequest (
        @NotBlank(message = "username은 필수로 입력해야 합니다.")
        String username,
        @NotBlank(message = "password는 필수로 입력해야 합니다.")
        String password,
        @NotBlank(message = "nickname은 필수로 입력해야 합니다.")
        String nickname
){
}
