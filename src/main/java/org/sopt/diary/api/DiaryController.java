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
        diaryService.updateDiary(diaryId, diaryUpdateRequest);
        return ResponseEntity.ok().build();
    }

    //일기 삭제
    @DeleteMapping("/api/diary/{diaryId}")
    ResponseEntity<Void> delete(@PathVariable long diaryId) {
        diaryService.deleteDiary(diaryId);
        return ResponseEntity.ok().build();



    }




}
