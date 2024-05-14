package com.example.demo.controller;

import com.example.demo.dto.request.auth.IDCheckRequestDto;
import com.example.demo.dto.request.auth.SignInRequestDto;
import com.example.demo.dto.request.auth.SignUpRequestDto;
import com.example.demo.dto.response.auth.IDCheckResponseDto;
import com.example.demo.dto.response.auth.SignInResponseDto;
import com.example.demo.dto.response.auth.SignUpResponseDto;
import com.example.demo.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<? super SignUpResponseDto> signUp(
            @RequestBody @Valid SignUpRequestDto dto
    ) {
        return authService.signUp(dto);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<? super SignInResponseDto> signUp(
            @RequestBody @Valid SignInRequestDto dto
    ) {
        return authService.signIn(dto);
    }

    @PostMapping("/id-check")
    public ResponseEntity<? super IDCheckResponseDto> signUp(
            @RequestBody @Valid IDCheckRequestDto dto
    ) {
        return authService.idCheck(dto);
    }


}
