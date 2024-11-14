package org.sopt.diary.enums;


import org.sopt.diary.exception.NotFoundException;
import org.sopt.diary.exception.code.ErrorCode;
import org.sopt.diary.exception.IllegalArgumentException;

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

        if(name == null) {
            return null;
        }
        for(Category category : Category.values()) {
            if(category.getName().equals(name)) {
                return category;
            }
        }
        throw new NotFoundException(ErrorCode.NOT_EXISTS_CATEGORY);
    }


}
