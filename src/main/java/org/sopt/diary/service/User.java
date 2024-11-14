package org.sopt.diary.service;

public class User {
    private final Long id;
    private final String username;
    private final String password;
    private final String nickname;

    public User(Long id, String username, String password, String nickname) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }
}
