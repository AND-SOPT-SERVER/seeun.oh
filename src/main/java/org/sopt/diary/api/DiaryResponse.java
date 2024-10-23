package org.sopt.diary.api;

public class DiaryResponse {
    private long id;
    private String title;


    public DiaryResponse(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

}
