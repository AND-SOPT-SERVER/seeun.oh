package org.sopt.diary.api.dto.response;

import org.sopt.diary.enums.Category;

import java.time.LocalDateTime;

public record DiaryDetailResponse(
        String title,
        String content,
        Long id,
        Category category,
        LocalDateTime createdAt,
        boolean isVisible
) {
    public static DiaryDetailResponse of(final String title, final String content, final Long id, final Category category,final LocalDateTime createdAt, boolean isVisible) {
        return new DiaryDetailResponse(title, content, id, category, createdAt, isVisible);
    }
}
