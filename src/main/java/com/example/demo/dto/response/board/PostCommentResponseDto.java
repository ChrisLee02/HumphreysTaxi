package com.example.demo.dto.response.board;

import com.example.demo.common.ResponseCode;
import com.example.demo.common.ResponseMessage;
import com.example.demo.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PostCommentResponseDto extends ResponseDto {
    public PostCommentResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<PostCommentResponseDto> success() {
        PostCommentResponseDto result = new PostCommentResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistUser() {
        return ResponseDto.noExistUser();
    }

    public static ResponseEntity<ResponseDto> noExistBoard() {
        return ResponseDto.noExistBoard();
    }

    public static ResponseEntity<ResponseDto> noPermission() {
        return ResponseDto.noPermission();
    }

}

