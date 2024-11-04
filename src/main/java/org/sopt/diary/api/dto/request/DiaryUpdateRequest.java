package org.sopt.diary.api.dto.request;

import jakarta.validation.constraints.Size;

public record DiaryUpdateRequest(
        @Size(max=30, message="글자수는 30자 이하로 작성해야 합니다.")
        String content
) {
}
