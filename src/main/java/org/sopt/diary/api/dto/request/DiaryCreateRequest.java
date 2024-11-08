package org.sopt.diary.api.dto.request;

import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.sopt.diary.enums.Category;

public record DiaryCreateRequest (
        @NotBlank(message = "제목은 필수로 작성해야 합니다.")
        @Size(min=1, max=10, message="제목은 1~10자 이내로 작성해야 합니다.")
        String title,
        @NotBlank(message = "내용은 필수로 작성해야 합니다.")
        @Size(max=30, message="내용은 1~30자 이내로 작성해야 합니다.")
        String content,
        @NotBlank(message = "카테고리는 필수로 작성해야 합니다.")
        String category,
        @NotNull(message = "공개 여부를 선택해야 합니다.")
        boolean isVisible
)
{
}
