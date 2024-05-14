package com.example.demo.dto.response.auth;

import com.example.demo.common.ResponseCode;
import com.example.demo.common.ResponseMessage;
import com.example.demo.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class SignUpResponseDto extends ResponseDto {

    private final String token;
    private final Long expirationInSec;

    public SignUpResponseDto(String token, Long expirationInSec) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.token = token;
        this.expirationInSec = expirationInSec;
    }

    public static ResponseEntity<SignUpResponseDto> success(String token, Long expirationInSec) {
        SignUpResponseDto result = new SignUpResponseDto(token, expirationInSec);
        return ResponseEntity.ok().body(result);
    }

    public static ResponseEntity<ResponseDto> duplicateID() {
        ResponseDto result = new ResponseDto(ResponseCode.DUPLICATE_ID, ResponseMessage.DUPLICATE_ID);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> duplicateNickname() {
        ResponseDto result = new ResponseDto(ResponseCode.DUPLICATE_NICKNAME, ResponseMessage.DUPLICATE_NICKNAME);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

}
