package org.sopt.diary.repository;


import jakarta.persistence.*;
import org.sopt.diary.enums.Category;

import java.time.LocalDateTime;

@Entity
@Table(name="diary")
public class DiaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //user만 조회
    @JoinColumn(name="user_id", nullable=false)
    private UserEntity user;

    @Column(name="title", unique = true, nullable = false)
    private String title;

    @Column(name="content")
    private String content;

    @Column(name="date", updatable = false) //초기 설정 이후 변하지 않음
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name="category", nullable = false)
    private Category category;

    @Column(name="is_visible", nullable = false)
    private boolean isVisible;
    

    public DiaryEntity() {}
    public DiaryEntity(UserEntity user, String title, String content, Category category) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.category = category;
        this.createdAt = LocalDateTime.now();
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

    public void setContent(String content) {
        this.content = content;
    }

    public Category getCategory() { return category; }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public UserEntity getUser() {
        return user;
    }

}
