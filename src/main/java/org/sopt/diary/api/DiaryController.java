package org.sopt.diary.api;

import org.sopt.diary.service.Diary;
import org.sopt.diary.service.DiaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    //일기 작성
    @PostMapping("/api/diary")
    void post(@RequestBody DiaryRequest diaryRequest) {
        // title 글자 수 확인
        if (diaryRequest.getTitle().length() > 30) {
            throw new IllegalArgumentException("제목은 30자 이내여야 합니다.");
        }
        // content 글자 수 확인
        if (diaryRequest.getContent().length() > 30) {
            throw new IllegalArgumentException("내용은 30자 이내여야 합니다.");
        }
        diaryService.createDiary(diaryRequest.getTitle(), diaryRequest.getContent());

    }

    //일기 목록 조회
    @GetMapping("/api/diary")
    ResponseEntity<DiaryListResponse> get() {

        //service로 부터 가져온 diaryList
        List<Diary> diaryList = diaryService.getList();

        //client와 협의한 인터페이스로 변환
        List<DiaryResponse> diaryResponseList = new ArrayList<>();
        for(Diary diary : diaryList) {
            diaryResponseList.add(new DiaryResponse(diary.getId(), diary.getTitle()));
        }

        return ResponseEntity.ok(new DiaryListResponse(diaryResponseList));
    }

    //일기 상세 조회
    @GetMapping("/api/diary/{diaryId}")
    ResponseEntity<DiaryDetailResponse> getDetail(@PathVariable long diaryId) {
        // service에서 다이어리 상세 정보를 가져옴
        Diary diary = diaryService.getDiaryById(diaryId);

        // Diary를 DiaryDetailResponse 변환
        DiaryDetailResponse diaryDetailResponse = new DiaryDetailResponse(diary.getId(), diary.getTitle(), diary.getContent(), diary.getCreatedAt());
        return ResponseEntity.ok(diaryDetailResponse);
    }

    //일기 수정
    @PatchMapping("/api/diary/{diaryId}")
    void patch(@PathVariable long diaryId, @RequestBody DiaryRequest diaryRequest) {
        // title 글자 수 확인
        if (diaryRequest.getTitle().length() > 30) {
            throw new IllegalArgumentException("제목은 30자 이내여야 합니다.");
        }
        // content 글자 수 확인
        if (diaryRequest.getContent().length() > 30) {
            throw new IllegalArgumentException("내용은 30자 이내여야 합니다.");
        }
        diaryService.updateDiary(diaryId, diaryRequest.getTitle(), diaryRequest.getContent());
    }

    //일기 삭제
    @DeleteMapping("/api/diary/{diaryId}")
    void delete(@PathVariable long diaryId) {
        diaryService.deleteDiary(diaryId);
    }




}
