package org.sopt.seminar1;
import org.sopt.seminar1.Main.UI.IdNotExistException;

import java.util.List;

public class DiaryService {
    private final DiaryRepository diaryRepository = new DiaryRepository();

    void writeDairy(final String body) {
            final Diary diary = new Diary(null, body);
            diaryRepository.save(diary);
    }
    List<Diary> getDiaryList() {
        return diaryRepository.findAll();
    }

    void patchDiary(final Long id, final String body) {
        //입력받은 id가 index에 존재하는지 확인
        if(diaryRepository.existsById(id)) {
            diaryRepository.patch(id, body);
        } else {
            throw new IdNotExistException();
        }

    }

    void deleteDiary(final Long id) {
        //입력받은 id가 index에 존재하는지 확인
        if(diaryRepository.existsById(id)) {
            diaryRepository.delete(id);
        } else {
            throw new IdNotExistException();
        }

    }

    void restoreDiary(final Long id) {
        if(diaryRepository.existsByDeletedId(id)) {
            diaryRepository.restore(id);
        } else {
            throw new IdNotExistException();
        }
    }

    List<Diary> getDeletedDiaryList() {
        return diaryRepository.findDeletedAll();
    }



}
