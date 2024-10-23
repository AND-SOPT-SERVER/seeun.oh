package org.sopt.diary.api;

public class DiaryRequest {
    private String title;
    private String content;

    public DiaryRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }



}
