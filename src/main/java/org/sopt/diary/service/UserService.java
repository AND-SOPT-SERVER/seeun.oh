package org.sopt.diary.service;

import org.sopt.diary.api.dto.request.LoginRequest;
import org.sopt.diary.api.dto.request.SignupRequest;
import org.sopt.diary.exception.NotFoundException;
import org.sopt.diary.exception.code.ErrorCode;
import org.sopt.diary.repository.UserEntity;
import org.sopt.diary.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.sopt.diary.exception.IllegalArgumentException;


@Component
public class UserService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity findUser(final String username, final String password) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXISTS_WITH_USERNAME));
        if(!user.getPassword().equals(password)) {
            throw new IllegalArgumentException(ErrorCode.WRONG_PASSWORD);
        }
        return user;
    }

//    public UserEntity findById(final Long userId) {
//        return userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
//    }

    public UserEntity signUp(SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.username())) {
            throw new IllegalArgumentException(ErrorCode.ALREADY_EXISTS_USERNAME);
        }
        UserEntity user = new UserEntity(signupRequest.username(), signupRequest.password(), signupRequest.nickname());
        return userRepository.save(user);
    }

    public Long logIn(LoginRequest loginRequest) {
        UserEntity user = userRepository.findByUsername(loginRequest.username()).orElseThrow(()-> new IllegalArgumentException(ErrorCode.NOT_EXISTS_WITH_USERNAME));

        if(!user.getPassword().equals(loginRequest.password())) {
            throw new IllegalArgumentException(ErrorCode.WRONG_PASSWORD);
        }

        return user.getId();
    }

}
