package com.example.demo.dto.object;

import com.example.demo.entity.BoardListViewEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardListItem {
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

    public BoardListItem(BoardListViewEntity boardListViewEntity) {
        this.boardId = boardListViewEntity.getBoardId();
        this.title = boardListViewEntity.getTitle();
        this.startingPoint = boardListViewEntity.getStartingPoint();
        this.destinationPoint = boardListViewEntity.getDestinationPoint();
        this.departTime = boardListViewEntity.getDepartTime();
        this.joiningUserCnt = boardListViewEntity.getJoiningUserCnt();
        this.username = boardListViewEntity.getUsername();
        this.unit = boardListViewEntity.getUnit();
        this.profileImage = boardListViewEntity.getProfileImage();
        this.writeDatetime = boardListViewEntity.getWriteDatetime();
        this.closed = boardListViewEntity.getClosed();
    }

    public static List<BoardListItem> getList(List<BoardListViewEntity> boardListViewEntities) {
        List<BoardListItem> list = new ArrayList<>();
        for (BoardListViewEntity boardListViewEntity : boardListViewEntities) {
            list.add(new BoardListItem(boardListViewEntity));
        }
        return list;
    }

}
