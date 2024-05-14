package com.example.demo.entity;

import com.example.demo.dto.request.board.PostBoardRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "board")
@Table(name = "board")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;
    private String startingPoint;
    private String destinationPoint;
    private LocalDateTime departTime;
    private String writerId;
    private boolean closed;
    private LocalDateTime writeDatetime;
    private int joiningUserCnt;

    public BoardEntity(PostBoardRequestDto dto, String userId) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.startingPoint = dto.getStartingPoint();
        this.destinationPoint = dto.getDestinationPoint();
        this.departTime = dto.getDepartTime();
        this.writerId = userId;
        this.closed = false;
        this.writeDatetime = LocalDateTime.now();
        this.joiningUserCnt = 1;
    }

    public void increaseJoiningUserCnt() {
        joiningUserCnt++;
    }

    public void decreaseJoiningUserCnt() {
        joiningUserCnt--;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}
