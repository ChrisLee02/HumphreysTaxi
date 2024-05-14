package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "comment_view")
@Table(name = "comment_view")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentViewEntity {
    @Id
    private int id;
    private int boardId;
    private String content;
    private LocalDateTime writeDatetime;
    private String username;
    private String unit;
    private String profileImage;
    private String writerId;
}
