package com.example.demo.service;


import com.example.demo.dto.request.auth.IDCheckRequestDto;
import com.example.demo.dto.request.auth.SignInRequestDto;
import com.example.demo.dto.request.auth.SignUpRequestDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.provider.JwtProvider;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.implement.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AuthServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtProvider jwtProvider;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        //@Mock은 자동으로 DI가 됨.
        //@InjectMocks를 DI하기 위해 아래 코드를 실행한다.
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIdCheck() {
        // Mocking
        IDCheckRequestDto dto = new IDCheckRequestDto();
        dto.setId("testId");
        IDCheckRequestDto dto2 = new IDCheckRequestDto();
        dto2.setId("testId2");
        //when 키워드로 종속된 객체의 동작을 구현과 상관없이 지정한다.
        when(userRepository.existsById("testId")).thenReturn(false);
        when(userRepository.existsById("testId2")).thenReturn(true);

        // Test
        ResponseEntity<?> response = authService.idCheck(dto);
        ResponseEntity<?> response2 = authService.idCheck(dto2);

        // Verify
        assertEquals(ResponseEntity.ok().body(response.getBody()), response);
        assertEquals(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response2.getBody()), response2);
        //verify로 종속된 객체들의 동작까지 검증한다.
        verify(userRepository, times(1)).existsById("testId");
        verify(userRepository, times(1)).existsById("testId2");
    }

    @Test
    void testSignIn() {
        // Mocking
        SignInRequestDto dto = new SignInRequestDto();
        dto.setId("testId");
        dto.setPassword("testPassword");

        SignInRequestDto dto2 = new SignInRequestDto();
        dto2.setId("testId");
        dto2.setPassword("testPassword2");

        UserEntity userEntity = new UserEntity("testId", "encodedPassword");
        when(userRepository.findById("testId")).thenReturn(Optional.of(userEntity));
        when(passwordEncoder.matches("testPassword", "encodedPassword")).thenReturn(true);
        when(passwordEncoder.matches("testPassword2", "encodedPassword")).thenReturn(false);
        when(jwtProvider.createToken("testId")).thenReturn("testToken");

        // Test
        ResponseEntity<?> response = authService.signIn(dto);
        ResponseEntity<?> response2 = authService.signIn(dto2);

        // Verify
        assertEquals(ResponseEntity.ok().body(response.getBody()), response);
        assertEquals(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response2.getBody()), response2);
        verify(userRepository, times(2)).findById("testId");
        verify(passwordEncoder, times(1)).matches("testPassword", "encodedPassword");
        verify(passwordEncoder, times(1)).matches("testPassword2", "encodedPassword");
        verify(jwtProvider, times(1)).createToken("testId");
    }

    @Test
    void testSignUp() {
        // Mocking
        SignUpRequestDto dto = new SignUpRequestDto();
        dto.setId("testId");
        dto.setPassword("testPassword");
        dto.setUsername("testUsername");
        when(userRepository.existsById("testId")).thenReturn(false);
        when(userRepository.existsByUsername("testUsername")).thenReturn(false);
        when(passwordEncoder.encode("testPassword")).thenReturn("encodedPassword");
        when(jwtProvider.createToken("testId")).thenReturn("testToken");

        // Test
        ResponseEntity<?> response = authService.signUp(dto);

        // Verify
        assertEquals(ResponseEntity.ok().body(response.getBody()), response);
//        assertEquals("testToken", ((SignUpResponseDto) response.getBody()).getToken());
        verify(userRepository, times(1)).existsById("testId");
        verify(userRepository, times(1)).existsByUsername("testUsername");
        verify(passwordEncoder, times(1)).encode("testPassword");
        verify(userRepository, times(1)).save(any());
        verify(jwtProvider, times(1)).createToken("testId");
    }
}
