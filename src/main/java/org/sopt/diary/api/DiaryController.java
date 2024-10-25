package org.sopt.diary.api;

import org.sopt.diary.service.Diary;
import org.sopt.diary.service.DiaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    //일기 작성
    @PostMapping("/api/diary")
    ResponseEntity<Map<String, String>> post(@RequestBody DiaryRequest diaryRequest) {
        try {
            // content 글자 수 확인
            if (diaryRequest.getContent().length() > 30) {
                throw new IllegalArgumentException("내용은 30자 이내여야 합니다.");
            }
            diaryService.createDiary(diaryRequest.getTitle(), diaryRequest.getContent());

            // Map.of()를 통해 HashMap 명시적 생성없이 Map 즉시 반환
            return ResponseEntity.status(200).body(Map.of("message", "일기 작성을 성공했습니다."));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "사용자 정보를 읽어올 수 없습니다."));
        }

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
    ResponseEntity<Map<String, String>> patch(@PathVariable long diaryId, @RequestBody DiaryRequest diaryRequest) {
        try {
            // content 글자 수 확인
            if (diaryRequest.getContent().length() > 30) {
                throw new IllegalArgumentException("내용은 30자 이내여야 합니다.");
            }
            diaryService.updateDiary(diaryId, diaryRequest.getTitle(), diaryRequest.getContent());

            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "일기 수정에 성공했습니다."));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "사용자 정보를 읽어올 수 없습니다."));
        }

    }

    //일기 삭제
    @DeleteMapping("/api/diary/{diaryId}")
    ResponseEntity<Map<String, String>> delete(@PathVariable long diaryId) {
        try {
            diaryService.deleteDiary(diaryId);
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "일기 삭제에 성공했습니다."));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "사용자 정보를 읽어올 수 없습니다."));
        }


    }




}
