package org.sopt.seminar1;


import java.util.List;

public class DiaryService {
    private final DiaryRepository diaryRepository = new DiaryRepository();

    void writeDairy(final String body) {
        if(body.length()<31) {
            final Diary diary = new Diary(null, body);
            diaryRepository.save(diary);
        }
    }
    List<Diary> getDiaryList() {
        return diaryRepository.findAll();
    }



}
