package org.sopt.diary.api;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DiaryDetailResponse {
    private long id;
    private String title;
    private String content;
    private String createdAt;

    public DiaryDetailResponse(long id, String title, String content, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
