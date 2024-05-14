package com.example.demo.dto.object;

import com.example.demo.entity.CommentViewEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentListItem {
    private int id;
    private int boardId;
    private String content;
    private LocalDateTime writeDatetime;
    private String username;
    private String unit;
    private String profileImage;
    private String writerId;

    public CommentListItem(CommentViewEntity commentViewEntity) {
        this.id = commentViewEntity.getId();
        this.boardId = commentViewEntity.getBoardId();
        this.content = commentViewEntity.getContent();
        this.writeDatetime = commentViewEntity.getWriteDatetime();
        this.username = commentViewEntity.getUsername();
        this.unit = commentViewEntity.getUnit();
        this.profileImage = commentViewEntity.getProfileImage();
        this.writerId = commentViewEntity.getWriterId();
    }

    public static List<CommentListItem> getList(List<CommentViewEntity> commentViewEntities) {
        List<CommentListItem> list = new ArrayList<>();
        for (CommentViewEntity commentViewEntity : commentViewEntities) {
            list.add(new CommentListItem(commentViewEntity));
        }
        return list;
    }
}
