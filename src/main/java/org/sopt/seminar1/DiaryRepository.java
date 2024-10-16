package org.sopt.seminar1;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import static java.lang.Integer.parseInt;


public class DiaryRepository {
    private final Map<Long, String> storage = new ConcurrentHashMap<>();
    private final Map<Long, String> deletedStorage = new ConcurrentHashMap<>();
    private final AtomicLong numbering = new AtomicLong();


    void save(final Diary diary) {
        //채번 과정
        final long id = numbering.addAndGet(1);

        //저장 과정
        storage.put(id, diary.getBody());
    }


    List<Diary> findAll() {
        // (1) diary 담을 자료 구조
        final List<Diary> diaryList = new ArrayList<>();

        // (2) 저장한 값을 불러오는 반복 구조
        for(long index=1; index <= numbering.longValue(); index++) {
            final String body = storage.get(index);
            
            //delete될 경우, null이 저장되므로 제외해야 함
            if(body != null) {
                //(2-1) 불러온 값을 구성한 자료구조로 이관
                diaryList.add(new Diary(index, body));
            }

        }

        //(3) 불러온 자료구조 응답
        return diaryList;
    }


    void patch(final Long id, final String body) {
        //body가 null이면 삭제된 일기 -> update x
        final String getBody = storage.get(id);
        if(getBody == null) {
            return;
        }
        storage.put(id, body);

    }

    void delete(final Long id) {
        String deletedBody = storage.remove(id);
        deletedStorage.put(id, deletedBody);
    }

    void restore(final Long id) {
        String restoreBody = deletedStorage.get(id);
        storage.put(id, restoreBody);
        System.out.println(id+"번 일기 복구 완료");
    }

    List<Diary> findDeletedAll() {
        // (1) 삭제된 일기 목록 담을 구조
        final List<Diary> deletedDiaryList = new ArrayList<>();

        // (2) deletedStorage에 id 있으면 deletedDiaryList에 add
        for(Long id : deletedStorage.keySet()) {
            deletedDiaryList.add(new Diary(id, deletedStorage.get(id)));
        }
        //(3) 불러온 자료구조 응답
        return deletedDiaryList;
    }

    boolean existsById(final Long id) {
        return storage.containsKey(id);
    }

    boolean existsByDeletedId(final Long id) {
        return deletedStorage.containsKey(id);
    }


}
