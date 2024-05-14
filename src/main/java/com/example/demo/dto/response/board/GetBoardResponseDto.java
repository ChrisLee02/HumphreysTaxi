package com.example.demo.dto.response.board;

import com.example.demo.common.ResponseCode;
import com.example.demo.common.ResponseMessage;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.entity.BoardViewEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
public class GetBoardResponseDto extends ResponseDto {
    private Integer boardId;
    private String title;
    private String content;
    private String startingPoint;
    private String destinationPoint;
    private LocalDateTime departTime;
    private LocalDateTime writeDatetime;
    private boolean closed;
    private String writerId;
    private String profileImage;
    private String unit;
    private String username;
    private int joiningUserCnt;

    public GetBoardResponseDto(BoardViewEntity boardViewEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        this.boardId = boardViewEntity.getBoardId();
        this.title = boardViewEntity.getTitle();
        this.content = boardViewEntity.getContent();
        this.startingPoint = boardViewEntity.getStartingPoint();
        this.destinationPoint = boardViewEntity.getDestinationPoint();
        this.departTime = boardViewEntity.getDepartTime();
        this.writeDatetime = boardViewEntity.getWriteDatetime();
        this.closed = boardViewEntity.isClosed();
        this.writerId = boardViewEntity.getWriterId();
        this.profileImage = boardViewEntity.getProfileImage();
        this.username = boardViewEntity.getUsername();
        this.unit = boardViewEntity.getUnit();
        this.joiningUserCnt = boardViewEntity.getJoiningUserCnt();
    }

    public static ResponseEntity<GetBoardResponseDto> success(BoardViewEntity boardViewEntity) {
        GetBoardResponseDto result = new GetBoardResponseDto(boardViewEntity);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistBoard() {
        return ResponseDto.noExistBoard();
    }


}
