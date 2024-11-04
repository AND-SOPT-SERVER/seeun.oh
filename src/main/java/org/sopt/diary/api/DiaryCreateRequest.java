package org.sopt.diary.api;

import jakarta.validation.constraints.Size;

public record DiaryCreateRequest (
        String title,
        @Size(max=30, message="글자수는 30자 이하로 작성해야 합니다.")
        String content
)
{
}
