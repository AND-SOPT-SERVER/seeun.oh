package org.sopt.diary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> {
    // 제목이 이미 존재하는지 확인하는 쿼리 메서드
    boolean existsByTitle(String title);
}
