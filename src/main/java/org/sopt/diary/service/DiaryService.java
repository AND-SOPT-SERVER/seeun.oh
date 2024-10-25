package org.sopt.diary.service;

import org.sopt.diary.repository.DiaryEntity;
import org.sopt.diary.repository.DiaryRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private static final Duration DIARY_WRITE_COOLDOWN = Duration.ofMinutes(0);

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    public void createDiary(String title, String content) {
        LocalDateTime now = LocalDateTime.now();
        DiaryEntity lastestDiary = diaryRepository.findTopByOrderByCreatedAtDesc();

        //lastestDiary가 null이면, 작성된 일기가 없는 것.
        if(lastestDiary != null) {
            //null이 아닌 경우, 경과 시간 계산
            Duration elapsedTime = Duration.between(lastestDiary.getCreatedAt(), now);

            // 쿨다운이 지났는지 확인
            if (elapsedTime.compareTo(DIARY_WRITE_COOLDOWN) < 0) {
                throw new IllegalStateException("5분에 하나씩 작성 가능");
                //추후 남은 시간 계산
            }
        }

        //제목 중복 검사
        if (diaryRepository.existsByTitle(title)) {
            throw new IllegalArgumentException("이미 존재하는 제목입니다. 다른 제목을 입력하세요.");
        }

        diaryRepository.save(new DiaryEntity(title, content));
    }

    public List<Diary> getList() {

        //repository로부터 DiaryEntity 가져옴
        //final List<DiaryEntity> diaryEntityList = diaryRepository.findAll();

        //목록 조회 10개 제한
        Pageable pageable =PageRequest.of(0, 10, Sort.by("id").descending());

        // Page 객체로 페이징된 결과를 가져옴
        List<DiaryEntity> diaryEntityList = diaryRepository.findAll(pageable).getContent();


        //DiaryEntity -> Diary로 변환해주는 작업
        final List<Diary> diaryList = new ArrayList<>();

        for (DiaryEntity diaryEntity : diaryEntityList) {
            diaryList.add(new Diary(diaryEntity.getId(), diaryEntity.getTitle(), diaryEntity.getContent(), diaryEntity.getCreatedAt()));
        }
        return diaryList;
    }

    public Diary getDiaryById(long id) {
        // repository로부터 id로 DiaryEntity를 가져옴
        Optional<DiaryEntity> diaryEntityOptional = diaryRepository.findById(id);

        // DiaryEntity가 존재하는지 확인
        if(diaryEntityOptional.isPresent()) {
            DiaryEntity diaryDetail = diaryEntityOptional.get();

            // DiaryEntity -> Diary로 변환 후 반환
            return new Diary(diaryDetail.getId(), diaryDetail.getTitle(), diaryDetail.getContent(), diaryDetail.getCreatedAt());
        } else {
            // id에 해당하는 DiaryEntity가 없는 경우 예외 처리
            throw new NoSuchElementException("존재하지 않는 일기 id입니다.");
        }
    }

    public void updateDiary(long id, String content) {
        // id에 해당하는 DiaryEntity가 존재하는지 확인
        DiaryEntity diaryEntity = diaryRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("존재하지 않는 일기 id입니다."));
        diaryEntity.setContent(content);
        diaryRepository.save(diaryEntity);
    }

    public void deleteDiary(long id) {
        // id에 해당하는 DiaryEntity가 존재하는지 확인
        Optional<DiaryEntity> diaryEntityOptional = diaryRepository.findById(id);

        if (diaryEntityOptional.isPresent()) {
            // 다이어리 존재하면 삭제
            diaryRepository.deleteById(id);
        } else {
            // 존재하지 않을 경우 예외 처리
            throw new NoSuchElementException("존재하지 않는 일기 id입니다.");
        }
    }
}
