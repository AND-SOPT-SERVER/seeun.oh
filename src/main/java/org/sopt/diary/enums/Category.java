package org.sopt.diary.enums;

import org.sopt.diary.exception.DiaryException;

import java.util.Arrays;

import static org.sopt.diary.code.FailureCode.NOT_EXISTS_CATEGORY;

public enum Category {
    FOOD("음식"),
    SCHOOL("학교"),
    MOVIE("영화"),
    EXERCISE("운동");

    private final String name;
    Category(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public static Category findCategory(String name) {
        if(name == null) return null;
        return Arrays.stream(Category.values()).filter(category -> category.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new DiaryException(NOT_EXISTS_CATEGORY));
    }


}
