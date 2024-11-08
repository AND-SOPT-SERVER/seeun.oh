package org.sopt.diary.api.dto.response;

import org.sopt.diary.enums.Category;

import java.time.LocalDateTime;
import java.util.List;

public record DiaryListResponse(
    List<DiaryResponse> diaryList
){
    public record DiaryResponse(
            Long id,
            Long userId,
            String title,
            String nickname,
            LocalDateTime createdAt
    ) {
        public static DiaryResponse of(final Long id, final Long userId, final String title,  final String nickname, final LocalDateTime createdAt) {
            return new DiaryResponse(id, userId, title, nickname, createdAt);
        }
    }

    public static DiaryListResponse of(List<DiaryResponse> diaryList) {
        return new DiaryListResponse(diaryList);
    }
}






