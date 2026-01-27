package com.management.HospitalMangementSystem.controller;

import com.management.HospitalMangementSystem.dto.LoginRequestDto;
import com.management.HospitalMangementSystem.dto.LoginResponseDto;
import com.management.HospitalMangementSystem.dto.SignUpRequestDto;
import com.management.HospitalMangementSystem.dto.SignUpResponseDto;
import com.management.HospitalMangementSystem.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signup(@RequestBody SignUpRequestDto signUpRequestDto){
        return ResponseEntity.ok(authService.signup(signUpRequestDto));
    }
}
