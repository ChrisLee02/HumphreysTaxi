package com.example.demo.dto.response.board;

import com.example.demo.common.ResponseCode;
import com.example.demo.common.ResponseMessage;
import com.example.demo.dto.object.JoiningUserListItem;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.entity.UserEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetJoinListResponseDto extends ResponseDto {

    private List<JoiningUserListItem> joiningUserListItemList;

    public GetJoinListResponseDto(List<UserEntity> userEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.joiningUserListItemList = JoiningUserListItem.getList(userEntities);
    }

    public static ResponseEntity<GetJoinListResponseDto> success(List<UserEntity> userEntities) {
        GetJoinListResponseDto result = new GetJoinListResponseDto(userEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistBoard() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
