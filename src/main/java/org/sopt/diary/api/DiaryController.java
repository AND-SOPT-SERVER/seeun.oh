package org.sopt.diary.api;

import jakarta.validation.Valid;
import org.sopt.diary.api.dto.request.DiaryCreateRequest;
import org.sopt.diary.api.dto.response.DiaryDetailResponse;
import org.sopt.diary.api.dto.response.DiaryListResponse;
import org.sopt.diary.api.dto.request.DiaryUpdateRequest;
import org.sopt.diary.enums.Category;
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
    ResponseEntity<Void> post(@Valid @RequestBody DiaryCreateRequest diaryCreateRequest, @RequestHeader(value="username") String username, @RequestHeader(value="password") String password) {
            Category category = Category.findCategory(diaryCreateRequest.category());
            diaryService.createDiary(diaryCreateRequest.title(), diaryCreateRequest.content(), category, username, password, diaryCreateRequest.isVisible());
            return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    //일기 목록 조회 - 메인 홈
    @GetMapping("/api/diary")
    ResponseEntity<DiaryListResponse> get(@RequestParam(name="category", required = false) String category) {
        DiaryListResponse diaryListResponse = diaryService.getList(Category.findCategory(category));
        return ResponseEntity.ok(diaryListResponse);
    }


    //일기 목록 조회 - 내 일기 모아보기
    @GetMapping("/api/diary/mypage")
    ResponseEntity<DiaryListResponse> getMyList(@RequestHeader(value="username") String username, @RequestHeader(value="password") String password, @RequestParam(name="category", required = false) String category) {
        DiaryListResponse diaryListResponse = diaryService.getMyDiaryList(Category.findCategory(category), username, password);
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
    ResponseEntity<Map<String, String>> patch(@PathVariable long diaryId, @RequestBody DiaryUpdateRequest diaryUpdateRequest, @RequestHeader(value="username") String username, @RequestHeader(value="password") String password) {
        diaryService.updateDiary(diaryId, diaryUpdateRequest.content(), username, password, diaryUpdateRequest.isVisible());
        return ResponseEntity.ok().build();
    }

    //일기 삭제
    @DeleteMapping("/api/diary/{diaryId}")
    ResponseEntity<Void> delete(@PathVariable long diaryId, @RequestHeader(value="username") String username, @RequestHeader(value="password") String password) {
        diaryService.deleteDiary(diaryId, username, password);
        return ResponseEntity.ok().build();



    }




}
