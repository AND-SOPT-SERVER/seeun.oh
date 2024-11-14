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

    //최신순 정렬
    //카테고리 선택o
    List<DiaryEntity> findByCategoryAndIsVisibleTrueOrderByCreatedAtDesc(Category category);

    //카테고리 선택x
    List<DiaryEntity> findByIsVisibleTrueOrderByCreatedAtDesc();

    //유저 o, 카테고리 선택o,
    List<DiaryEntity> findByCategoryAndUserOrderByCreatedAtDesc(Category category, UserEntity user);

    //유저 o, 카테고리 선택x
    List<DiaryEntity> findByUserOrderByCreatedAtDesc(UserEntity user);


}
