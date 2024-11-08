package org.sopt.diary.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DiaryUpdateRequest(
        @NotBlank(message = "내용은 필수로 작성해야 합니다.")
        @Size(min=1, max=30, message="내용은 1~30자 이내로 작성해야 합니다.")
        String content
) {
}
