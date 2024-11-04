package org.sopt.diary.api.dto.response;

import java.util.List;

public record DiaryListResponse(
    List<DiaryResponse> diaryList
){
    public record DiaryResponse(
            long id,
            String title
    ) {
        public static DiaryResponse of(long id, String title) {
            return new DiaryResponse(id, title);
        }
    }

    public static DiaryListResponse of(List<DiaryResponse> diaryList) {
        return new DiaryListResponse(diaryList);
    }
}






