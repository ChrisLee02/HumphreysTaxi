package com.example.demo.dto.response.board;

import com.example.demo.common.ResponseCode;
import com.example.demo.common.ResponseMessage;
import com.example.demo.dto.object.BoardListItem;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.entity.BoardListViewEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetBoardListResponseDto extends ResponseDto {

    private List<BoardListItem> boardList;

    public GetBoardListResponseDto(List<BoardListViewEntity> boardListViewEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.boardList = BoardListItem.getList(boardListViewEntities);
    }

    public static ResponseEntity<GetBoardListResponseDto> success(List<BoardListViewEntity> boardListViewEntities) {
        GetBoardListResponseDto result = new GetBoardListResponseDto(boardListViewEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
