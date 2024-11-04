package org.sopt.diary.api;

import jakarta.validation.Valid;
import org.sopt.diary.service.DiaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    //일기 작성
    @PostMapping("/api/diary")
    ResponseEntity<Void> post(@Valid @RequestBody DiaryCreateRequest diaryCreateRequest) {

            diaryService.createDiary(diaryCreateRequest);
            return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    //일기 목록 조회
    @GetMapping("/api/diary")
    ResponseEntity<DiaryListResponse> get() {
        DiaryListResponse diaryListResponse = diaryService.getList();
        return ResponseEntity.ok(diaryListResponse);
    }

    //일기 상세 조회
    @GetMapping("/api/diary/{diaryId}")
    ResponseEntity<DiaryDetailResponse> getDetail(@PathVariable long diaryId) {
        DiaryDetailResponse diaryDetailResponse = diaryService.getDiaryById(diaryId);
        return ResponseEntity.ok(diaryDetailResponse);
    }

    //일기 수정
    @PatchMapping("/api/diary/{diaryId}")
    ResponseEntity<Map<String, String>> patch(@PathVariable long diaryId, @RequestBody DiaryUpdateRequest diaryUpdateRequest) {
        try {
            // content 글자 수 확인
            if (diaryUpdateRequest.getContent().length() > 30) {
                throw new IllegalArgumentException("내용은 30자 이내여야 합니다.");
            }
            diaryService.updateDiary(diaryId, diaryUpdateRequest.getContent());

            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "일기 수정에 성공했습니다."));

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }

    }

    //일기 삭제
    @DeleteMapping("/api/diary/{diaryId}")
    ResponseEntity<Map<String, String>> delete(@PathVariable long diaryId) {
        try {
            diaryService.deleteDiary(diaryId);
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "일기 삭제에 성공했습니다."));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }


    }




}
