package com.example.demo.controller;

import com.example.demo.dto.request.user.PatchUserRequestDto;
import com.example.demo.dto.response.user.GetUserResponseDto;
import com.example.demo.dto.response.user.PatchUserResponseDto;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<? super GetUserResponseDto> getUser(
            @PathVariable String userId
    ) {
        return userService.getUser(userId);
    }

    @PatchMapping("/{patchUserId}")
    public ResponseEntity<? super PatchUserResponseDto> patchUser(
            @RequestBody @Valid PatchUserRequestDto dto,
            @PathVariable String patchUserId,
            @AuthenticationPrincipal String logInUserId
    ) {
        return userService.patchUser(dto, patchUserId, logInUserId);
    }


}
