package com.example.demo.dto.response.board;

import com.example.demo.common.ResponseCode;
import com.example.demo.common.ResponseMessage;
import com.example.demo.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class PostBoardResponseDto extends ResponseDto {
    private Integer id;

    public PostBoardResponseDto(Integer id) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.id = id;
    }

    public static ResponseEntity<PostBoardResponseDto> success(Integer id) {
        PostBoardResponseDto result = new PostBoardResponseDto(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistUser() {
        return ResponseDto.noExistUser();
    }

    public static ResponseEntity<ResponseDto> forbiddenToJoin() {
        ResponseDto result = new ResponseDto(ResponseCode.FORBIDDEN_TO_JOIN, ResponseMessage.FORBIDDEN_TO_JOIN);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
    }


}
