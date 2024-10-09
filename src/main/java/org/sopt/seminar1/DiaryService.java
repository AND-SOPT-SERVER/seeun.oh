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
        if(diaryRepository.existsById(id)) {
            diaryRepository.update(id, body);
        } else {
            throw new IdNotExistException();
        }

    }

    void deleteDiary(final Long id) {
        if(diaryRepository.existsById(id)) {
            diaryRepository.delete(id);
        } else {
            throw new IdNotExistException();
        }

    }



}
