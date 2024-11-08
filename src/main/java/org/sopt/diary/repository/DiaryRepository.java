package org.sopt.diary.repository;

import org.sopt.diary.enums.Category;
import org.sopt.diary.service.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> {

    DiaryEntity findTopByOrderByCreatedAtDesc();
    List<DiaryEntity> findTop10ByOrderByCreatedAtDesc();

    Optional<DiaryEntity> findByUserAndId(UserEntity user, Long id);
    List<DiaryEntity> findByCategory(Category category);
    List<DiaryEntity> findByCategoryAndUser(Category category, UserEntity user);
    List<DiaryEntity> findByUser(UserEntity user);
}
