package com.example.demo.dto.response.board;

import com.example.demo.common.ResponseCode;
import com.example.demo.common.ResponseMessage;
import com.example.demo.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PutJoinResponseDto extends ResponseDto {

    public PutJoinResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<PutJoinResponseDto> success() {
        PutJoinResponseDto result = new PutJoinResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistUser() {
        return ResponseDto.noExistUser();
    }

    public static ResponseEntity<ResponseDto> noExistBoard() {
        return ResponseDto.noExistBoard();
    }


    public static ResponseEntity<ResponseDto> alreadyFullBoard() {
        ResponseDto result = new ResponseDto(ResponseCode.ALREADY_FUlL_BOARD, ResponseMessage.ALREADY_FUlL_BOARD);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
    }

    public static ResponseEntity<ResponseDto> forbiddenToJoin() {
        ResponseDto result = new ResponseDto(ResponseCode.FORBIDDEN_TO_JOIN, ResponseMessage.FORBIDDEN_TO_JOIN);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
    }

    public static ResponseEntity<ResponseDto> noPermission() {
        return ResponseDto.noPermission();
    }


}
