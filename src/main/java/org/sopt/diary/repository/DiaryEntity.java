package org.sopt.diary.repository;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class DiaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column
    public String title;

    @Column
    public String content;

    @Column(updatable = false) //초기 설정 이후 변하지 않음
    public LocalDateTime createdAt;
    

    public DiaryEntity() {}
    public DiaryEntity(String title, String content) {
        this.title = title;
        this.content = content;
    }



    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now(); // 저장 시 자동으로 현재 시간 설정
    }

}
