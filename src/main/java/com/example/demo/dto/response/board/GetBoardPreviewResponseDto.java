package com.example.demo.dto.response.board;

import com.example.demo.common.ResponseCode;
import com.example.demo.common.ResponseMessage;
import com.example.demo.dto.object.BoardListItem;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.entity.BoardListViewEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetBoardPreviewResponseDto extends ResponseDto {

    private BoardListItem boardPreview;

    public GetBoardPreviewResponseDto(BoardListViewEntity boardListViewEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.boardPreview = new BoardListItem(boardListViewEntity);

    }

    public static ResponseEntity<GetBoardPreviewResponseDto> success(BoardListViewEntity boardListViewEntity) {
        GetBoardPreviewResponseDto result = new GetBoardPreviewResponseDto(boardListViewEntity);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistBoard() {
        return ResponseDto.noExistBoard();
    }


}
