package org.sopt.diary.api.dto.response;

import java.time.LocalDateTime;

public record DiaryDetailResponse(
        String title,
        String content,
        long id,
        LocalDateTime createdAt
) {
    public static DiaryDetailResponse of(final String title, final String content, final long id, final LocalDateTime createdAt) {
        return new DiaryDetailResponse(title, content, id, createdAt);
    }
}
