package org.sopt.diary.service;

import org.sopt.diary.api.dto.request.LoginRequest;
import org.sopt.diary.api.dto.request.SignupRequest;
import org.sopt.diary.repository.UserEntity;
import org.sopt.diary.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class UserService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity findUser(final String username, final String password) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("User not found")); //커스텀 에러 처리 필요
        if(!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Wrong password"); //커스텀 예외 처리 필요
        }
        return user;
    }

    public UserEntity findById(final Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found")); //예외 커스텀
    }

    public UserEntity signUp(SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.username())) {
            throw new IllegalArgumentException("이미 존재하는 username입니다."); //커스텀 예외 처리 필요
        }
        UserEntity user = new UserEntity(signupRequest.username(), signupRequest.password(), signupRequest.nickname());
        return userRepository.save(user);
    }

    public Long logIn(LoginRequest loginRequest) {
        UserEntity user = userRepository.findByUsername(loginRequest.username()).orElseThrow(()-> new IllegalArgumentException("로그인 실패 - 잘못된 username입니다."));

        if(!user.getPassword().equals(loginRequest.password())) {
            throw new IllegalArgumentException("로그인 실패 - 잘못된 password입니다.");
        }

        return user.getId();
    }

}
