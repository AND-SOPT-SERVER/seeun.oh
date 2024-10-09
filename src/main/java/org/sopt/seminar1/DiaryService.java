package org.sopt.seminar1;


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
        diaryRepository.update(id, body);
    }

    void deleteDiary(final Long id) {

        diaryRepository.delete(id);
    }



}
