package com.example.demo.dto.response.user;

import com.example.demo.common.ResponseCode;
import com.example.demo.common.ResponseMessage;
import com.example.demo.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PatchUserResponseDto extends ResponseDto {

    public PatchUserResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<PatchUserResponseDto> success() {
        PatchUserResponseDto result = new PatchUserResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistUser() {
        return ResponseDto.noExistUser();
    }

    public static ResponseEntity<ResponseDto> noPermission() {
        return ResponseDto.noPermission();
    }
}
