package com.example.demo.dto.response.auth;

import com.example.demo.common.ResponseCode;
import com.example.demo.common.ResponseMessage;
import com.example.demo.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class SignInResponseDto extends ResponseDto {

    private String token;
    private Long expirationInSec;

    public SignInResponseDto(String token, Long expirationInSec) {
//        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        super(ResponseCode.SUCCESS, "로그인 성공");

        this.token = token;
        this.expirationInSec = expirationInSec;
    }

    public static ResponseEntity<SignInResponseDto> success(String token, Long expirationInSec) {
        SignInResponseDto result = new SignInResponseDto(token, expirationInSec);
        // System.out.println(result.token);
        return ResponseEntity.ok().body(result);
    }

    public static ResponseEntity<ResponseDto> signInFail() {
        ResponseDto result = new ResponseDto(ResponseCode.SIGN_IN_FAIL, ResponseMessage.SIGN_IN_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
}
