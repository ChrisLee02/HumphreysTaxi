package com.example.demo.dto.response.board;

import com.example.demo.common.ResponseCode;
import com.example.demo.common.ResponseMessage;
import com.example.demo.dto.object.CommentListItem;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.entity.CommentViewEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetCommentListResponseDto extends ResponseDto {
    private List<CommentListItem> commentList;

    public GetCommentListResponseDto(List<CommentViewEntity> commentViewEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.commentList = CommentListItem.getList(commentViewEntities);
    }

    public static ResponseEntity<GetCommentListResponseDto> success(List<CommentViewEntity> commentViewEntities) {
        GetCommentListResponseDto result = new GetCommentListResponseDto(commentViewEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistBoard() {
        return ResponseDto.noExistBoard();
    }

    public static ResponseEntity<ResponseDto> noExistUser() {
        return ResponseDto.noExistUser();
    }

    public static ResponseEntity<ResponseDto> noPermission() {
        return ResponseDto.noPermission();
    }

}
