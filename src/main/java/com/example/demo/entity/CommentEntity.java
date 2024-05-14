package com.example.demo.entity;

import com.example.demo.dto.request.board.PostCommentRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "comment")
@Table(name = "comment")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;
    private int boardId;
    private String writerId;
    private LocalDateTime writeDatetime;

    public CommentEntity(PostCommentRequestDto dto, Integer boardId, String userId) {
        this.content = dto.getContent();
        this.boardId = boardId;
        this.writerId = userId;
        this.writeDatetime = LocalDateTime.now();
    }
}
