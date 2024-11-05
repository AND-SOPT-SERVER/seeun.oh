package org.sopt.diary.repository;

import org.sopt.diary.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
