package org.sopt.diary.repository;

import org.sopt.diary.service.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> {

    DiaryEntity findTopByOrderByCreatedAtDesc();
    List<Diary> findTop10ByOrderByCreatedAtDesc();
}
