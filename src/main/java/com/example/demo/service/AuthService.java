package com.example.demo.service;

import com.example.demo.dto.request.auth.IDCheckRequestDto;
import com.example.demo.dto.request.auth.SignInRequestDto;
import com.example.demo.dto.request.auth.SignUpRequestDto;
import com.example.demo.dto.response.auth.IDCheckResponseDto;
import com.example.demo.dto.response.auth.SignInResponseDto;
import com.example.demo.dto.response.auth.SignUpResponseDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<? super IDCheckResponseDto> idCheck(IDCheckRequestDto dto);

    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);

    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);
}
