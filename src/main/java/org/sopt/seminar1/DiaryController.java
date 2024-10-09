package org.sopt.seminar1;

import org.sopt.seminar1.Main.UI.BodyLengthException;
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
        //글자수 제한 검증
        validateLength(body);
        //입력받은 body 저장하기
        diaryService.writeDairy(body);

    }
    final void delete(final String id) {
        diaryService.deleteDiary(Long.parseLong(id));
    }
    final void patch(final String id, final String body) {
        //입력받은 id(index에 있다면)의 body 수정하기
        //글자 수 제한 검증
        validateLength(body);
        diaryService.patchDiary(Long.parseLong(id), body);
    }

    final void validateLength(String body) {
        if(body.length()>=31) {
            System.out.println(body.length()+"자");
            throw new BodyLengthException();
        }
    }

    enum Status {
        READY,
        RUNNING,
        FINISHED,
        ERROR,
    }
}