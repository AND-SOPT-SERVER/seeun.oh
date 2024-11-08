package org.sopt.diary.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "username은 필수로 작성해야 합니다.")
        String username,
        @NotBlank(message = "password는 필수로 작성해야 합니다.")
        String password
) {
}
