package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "board_view")
@Table(name = "board_view")
public class BoardViewEntity {
    @Id
    private int boardId;
    private String title;
    private String content;
    private String startingPoint;
    private String destinationPoint;
    private LocalDateTime departTime;
    private LocalDateTime writeDatetime;
    private boolean closed;
    private String writerId;
    private String profileImage;
    private String username;
    private String unit;
    private int joiningUserCnt;
}
