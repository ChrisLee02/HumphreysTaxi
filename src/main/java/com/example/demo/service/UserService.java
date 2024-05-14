package com.example.demo.service;

import com.example.demo.dto.request.user.PatchUserRequestDto;
import com.example.demo.dto.response.user.GetUserResponseDto;
import com.example.demo.dto.response.user.PatchUserResponseDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<? super GetUserResponseDto> getUser(String userId);

    ResponseEntity<? super PatchUserResponseDto> patchUser(PatchUserRequestDto dto, String patchUserId, String logInUserId);
}
