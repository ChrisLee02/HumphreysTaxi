package com.example.demo.service.implement;

import com.example.demo.dto.request.user.PatchUserRequestDto;
import com.example.demo.dto.response.user.GetUserResponseDto;
import com.example.demo.dto.response.user.PatchUserResponseDto;
import com.example.demo.entity.JoiningUserEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.JoiningUserRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JoiningUserRepository joiningUserRepository;

    @Override
    public ResponseEntity<? super GetUserResponseDto> getUser(String userId) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        Optional<JoiningUserEntity> byUserId = joiningUserRepository.findByUserId(userId);
        Integer boardId = null;
        if (byUserId.isPresent()) {
            boardId = byUserId.get().getBoardId();
        }

        if (userEntityOptional.isEmpty()) return GetUserResponseDto.noExistUser();

        return GetUserResponseDto.success(userEntityOptional.get(), boardId);
    }


    @Override
    public ResponseEntity<? super PatchUserResponseDto> patchUser(PatchUserRequestDto dto, String patchUserId, String logInUserId) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(patchUserId);
        if (userEntityOptional.isEmpty()) return PatchUserResponseDto.noExistUser();
        if (!patchUserId.equals(logInUserId)) return PatchUserResponseDto.noPermission();

        UserEntity userEntity = userEntityOptional.get();
        userEntity.patchUserEntity(dto);
        userRepository.save(userEntity);
        return PatchUserResponseDto.success();
    }
}
