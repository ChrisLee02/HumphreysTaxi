package com.example.demo.service.implement;

import com.example.demo.dto.request.auth.IDCheckRequestDto;
import com.example.demo.dto.request.auth.SignInRequestDto;
import com.example.demo.dto.request.auth.SignUpRequestDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.response.auth.IDCheckResponseDto;
import com.example.demo.dto.response.auth.SignInResponseDto;
import com.example.demo.dto.response.auth.SignUpResponseDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.provider.JwtProvider;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Value("${expirationInSec}")
    private Long expirationInSec;

    @Override
    public ResponseEntity<? super IDCheckResponseDto> idCheck(IDCheckRequestDto dto) {
        try {
            if (userRepository.existsById(dto.getId())) return IDCheckResponseDto.duplicatedID();
            return IDCheckResponseDto.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
        try {
            Optional<UserEntity> userEntityWrapped = userRepository.findById(dto.getId());

            if (userEntityWrapped.isEmpty()) return SignInResponseDto.signInFail();
            UserEntity userEntity = userEntityWrapped.get();

            String encodedPassword = userEntity.getPassword();
            String rawPassword = dto.getPassword();

            boolean matches = passwordEncoder.matches(rawPassword, encodedPassword);
            if (!matches) {
                return SignInResponseDto.signInFail();
            }

            String token = jwtProvider.createToken(userEntity.getId());
            return SignInResponseDto.success(token, expirationInSec);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {
        try {
            if (userRepository.existsById(dto.getId())) return SignUpResponseDto.duplicateID();
            if (userRepository.existsByUsername(dto.getUsername())) return SignUpResponseDto.duplicateNickname();

            String encodedPassword = passwordEncoder.encode(dto.getPassword());
            dto.setPassword(encodedPassword);
            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);

            String token = jwtProvider.createToken(userEntity.getId());
            return SignUpResponseDto.success(token, expirationInSec);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
    }
}
