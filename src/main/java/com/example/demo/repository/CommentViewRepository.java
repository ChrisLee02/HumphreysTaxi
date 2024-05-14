package com.example.demo.repository;

import com.example.demo.entity.CommentViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentViewRepository extends JpaRepository<CommentViewEntity, Integer> {
    List<CommentViewEntity> findByOrderByWriteDatetimeDesc();

}
