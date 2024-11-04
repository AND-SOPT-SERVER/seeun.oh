package org.sopt.diary.service;

import jakarta.persistence.EntityNotFoundException;
import org.sopt.diary.api.dto.request.DiaryCreateRequest;
import org.sopt.diary.api.dto.response.DiaryDetailResponse;
import org.sopt.diary.api.dto.response.DiaryListResponse;
import org.sopt.diary.api.dto.request.DiaryUpdateRequest;
import org.springframework.transaction.annotation.Transactional;
import org.sopt.diary.domain.DiaryEntity;
import org.sopt.diary.repository.DiaryRepository;
import org.springframework.stereotype.Component;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private static final Duration DIARY_WRITE_COOLDOWN = Duration.ofMinutes(5);

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }
    @Transactional
    public void createDiary(final DiaryCreateRequest diaryCreateRequest) {
        LocalDateTime now = LocalDateTime.now();
        DiaryEntity lastestDiary = diaryRepository.findTopByOrderByCreatedAtDesc();

//        //lastestDiary가 null이면, 작성된 일기가 없는 것.
//        if(lastestDiary != null) {
//            //null이 아닌 경우, 경과 시간 계산
//            Duration elapsedTime = Duration.between(lastestDiary.getCreatedAt(), now);
//
//            // 쿨다운이 지났는지 확인
//            if (elapsedTime.compareTo(DIARY_WRITE_COOLDOWN) < 0) {
//                throw new IllegalStateException("5분에 하나씩 작성 가능");
//                //추후 남은 시간 계산
//            }
//        }

        diaryRepository.save(new DiaryEntity(diaryCreateRequest.title(), diaryCreateRequest.content()));
    }

    public DiaryListResponse getList() {
        List<Diary> diaryList = diaryRepository.findTop10ByOrderByCreatedAtDesc();
        List<DiaryListResponse.DiaryResponse> diaryResponse = diaryList.stream().map(diary -> DiaryListResponse.DiaryResponse.of(diary.getId(), diary.getTitle())).toList();
        return DiaryListResponse.of(diaryResponse);
    }

    public DiaryDetailResponse getDiaryById(final long id) {
        DiaryEntity diaryEntity = findDiaryById(id);

        return DiaryDetailResponse.of(diaryEntity.getTitle(), diaryEntity.getContent(), diaryEntity.getId(), diaryEntity.getCreatedAt());
    }

    @Transactional
    public void updateDiary(final long id, final DiaryUpdateRequest diaryUpdateRequest) {
        // id에 해당하는 DiaryEntity가 존재하는지 확인
        DiaryEntity diaryEntity = findDiaryById(id);
        diaryEntity.setContent(diaryUpdateRequest.content());
    }

    public void deleteDiary(final long id) {
        DiaryEntity diaryEntity = findDiaryById(id);
        diaryRepository.delete(diaryEntity);
    }

    private DiaryEntity findDiaryById(final long id) {
        return diaryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 일기 id입니다."));
    }
}
