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
@Entity(name = "board_list_view")
@Table(name = "board_list_view")
public class BoardListViewEntity {
    @Id
    private int boardId;
    private String title;
    private String startingPoint;
    private String destinationPoint;
    private LocalDateTime departTime;
    private int joiningUserCnt;
    private String username;
    private String unit;
    private String profileImage;
    private LocalDateTime writeDatetime;
    private Boolean closed;
}
