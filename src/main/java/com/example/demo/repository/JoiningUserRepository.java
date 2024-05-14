package com.example.demo.repository;

import com.example.demo.entity.JoiningUserEntity;
import com.example.demo.entity.primaryKey.JoiningUserPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface JoiningUserRepository extends JpaRepository<JoiningUserEntity, JoiningUserPk> {
    @Transactional
    void deleteByBoardId(Integer boardId);

    Optional<JoiningUserEntity> findByUserId(String userId);

    List<JoiningUserEntity> findByBoardId(Integer boardId);

    boolean existsByUserId(String userId);

}
