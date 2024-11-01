package org.sopt.seminar1;

import org.sopt.seminar1.Main.UI.BodyLengthException;
import org.sopt.seminar1.Main.UI.InvalidInputException;
import java.util.ArrayList;
import java.util.List;


public class DiaryController {
    private Status status = Status.READY;
    private final DiaryService diaryService = new DiaryService();
    Status getStatus() {
        return status;
    }
    void boot() {
        this.status = Status.RUNNING;
    }
    void finish() {
        this.status = Status.FINISHED;
    }
    // APIS
    final List<Diary> getList() {
        return diaryService.getDiaryList();
    }
    final void post(final String body) {
        //입력 글자수 제한 검증
        validateInput(body);
        //입력받은 body 저장하기
        diaryService.writeDairy(body);

    }
    final void delete(final String id) {
        try {
            diaryService.deleteDiary(Long.parseLong(id));
        } catch (NumberFormatException e) { //입력 id 타입 검증
            throw new InvalidInputException();
        }

    }

    final void patch(final String id, final String body) {
        try {
            //입력 글자 수 제한 검증
            validateInput(body);
            diaryService.patchDiary(Long.parseLong(id), body);
        } catch (NumberFormatException e) { //입력 id 타입 검증
            throw new InvalidInputException();
        }

    }

    final void validateInput(String body) {
        //30자 초과
        if(body.length()>=31) {
            System.out.println(body.length()+"자");
            throw new BodyLengthException();
        }
        //입력 x
        if(body.trim().isEmpty()) {
            throw new InvalidInputException();
        }
    }

    final void restore(String id) {
        try {
            diaryService.restoreDiary(Long.parseLong(id));
        } catch (NumberFormatException e) {
            throw new InvalidInputException();
        }

    }

    final List<Diary> getDeletedList() {
        return diaryService.getDeletedDiaryList();
    }


    enum Status {
        READY,
        RUNNING,
        FINISHED,
        ERROR,
    }
}
