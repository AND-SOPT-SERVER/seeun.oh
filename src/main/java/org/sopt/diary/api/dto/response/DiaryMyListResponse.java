package org.sopt.diary.api.dto.response;

import java.time.LocalDateTime;

public record DiaryMyListResponse(
        Long id,
        String title,
        LocalDateTime createdAt
)
{
    public static DiaryMyListResponse of(final Long id, final String title, final LocalDateTime createdAt) {
        return new DiaryMyListResponse(id, title, createdAt);
    }
}
