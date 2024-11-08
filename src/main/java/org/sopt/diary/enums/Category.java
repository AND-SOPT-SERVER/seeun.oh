package org.sopt.diary.enums;


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
        throw new IllegalArgumentException("유효하지 않은 카테고리입니다." + name);
    }


}
