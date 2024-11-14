package org.sopt.diary.service;

import org.sopt.diary.api.dto.response.DiaryDetailResponse;
import org.sopt.diary.api.dto.response.DiaryListResponse;
import org.sopt.diary.api.dto.request.DiaryUpdateRequest;
import org.sopt.diary.exception.NotFoundException;
import org.sopt.diary.exception.IllegalArgumentException;
import org.sopt.diary.exception.code.ErrorCode;
import org.sopt.diary.repository.UserEntity;
import org.sopt.diary.enums.Category;
import org.sopt.diary.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.sopt.diary.repository.DiaryEntity;
import org.sopt.diary.repository.DiaryRepository;
import org.springframework.stereotype.Component;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private static final Duration DIARY_WRITE_COOLDOWN = Duration.ofMinutes(5);
    private final UserService userService;
    private final UserRepository userRepository;

    public DiaryService(DiaryRepository diaryRepository, UserService userService, UserRepository userRepository) {
        this.diaryRepository = diaryRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }
    @Transactional
    public void createDiary(final String title, final String content, final Category category, final String username, final String password, boolean isVisible) {
        UserEntity user = findUser(username, password);
        DiaryEntity diary = new DiaryEntity(user, title, content, category, isVisible);
        diaryRepository.save(diary);
    }

    public DiaryListResponse getList(final Category category) {
        List<DiaryListResponse.DiaryResponse> diaryResponse = getDiaryList(category, null); //유저x
        return DiaryListResponse.of(diaryResponse);
    }

    public DiaryListResponse getMyDiaryList(final Category category, final String username, final String password) {
        UserEntity user = findUser(username, password);
        List<DiaryListResponse.DiaryResponse> diaryResponse = getDiaryList(category, user); //유저o
        return DiaryListResponse.of(diaryResponse);
    }

    public DiaryDetailResponse getDiaryById(final long id) {
        DiaryEntity diaryEntity = findDiaryById(id);

        return DiaryDetailResponse.of(diaryEntity.getTitle(), diaryEntity.getContent(), diaryEntity.getId(), diaryEntity.getCategory(), diaryEntity.getCreatedAt(), diaryEntity.getVisible());
    }

    @Transactional
    public void updateDiary(final long id, final String content, final String username, final String password, boolean isVisible) {
        // user 확인
        UserEntity user = findUser(username, password);
        DiaryEntity diary = findDiaryWithUser(id, user);
        diary.setContent(content);
        diary.setVisible(isVisible);
    }



    @Transactional
    public void deleteDiary(final long id, final String username, final String password) {
        // user 확인
        UserEntity user = findUser(username, password);
        DiaryEntity diary = findDiaryWithUser(id, user);
        diaryRepository.delete(diary);
    }

    private DiaryEntity findDiaryById(final long id) {
        return diaryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXISTS_WITH_ID));
    }


    private UserEntity findUser(final String username, final String password) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(()-> new IllegalArgumentException(ErrorCode.NOT_EXISTS_WITH_USERNAME));
        if (!password.equals(user.getPassword())) {
            throw new IllegalArgumentException(ErrorCode.WRONG_PASSWORD);
        }
        return user;
    }

    private DiaryEntity findDiaryWithUser(final long id, final UserEntity user) {
        return diaryRepository.findByUserAndId(user, id).orElseThrow(()-> new NotFoundException(ErrorCode.NOT_EXISTS_WITH_USERNAME_AND_ID));
    }

    private List<DiaryListResponse.DiaryResponse> getDiaryList(final Category category, final UserEntity user) {
        List<DiaryEntity> diaryList;
        if(category == null && user == null) { //둘 다 x
            diaryList = diaryRepository.findByIsVisibleTrueOrderByCreatedAtDesc();
        } else if(user == null) { //전체의 category list
            diaryList = diaryRepository.findByCategoryAndIsVisibleTrueOrderByCreatedAtDesc(category);
        } else if(category == null) { //user의 전체 list
            diaryList = diaryRepository.findByUserOrderByCreatedAtDesc(user);
        } else { //user의 category list
            diaryList = diaryRepository.findByCategoryAndUserOrderByCreatedAtDesc(category, user);
        }
        return diaryList.stream().map(diary -> DiaryListResponse.DiaryResponse.of(diary.getId(), diary.getUser().getId(), diary.getTitle(), diary.getUser().getNickname(), diary.getCreatedAt())).toList();
    }
}
