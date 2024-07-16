

package com.calculetor.controller;

import com.calculetor.model.RefreshToken;
import com.calculetor.model.User;
import com.calculetor.service.AuthService;
import com.calculetor.service.JwtService;
import com.calculetor.service.RefreshTokenService;
import com.calculetor.utils.AuthResponse;
import com.calculetor.utils.LoginRequest;
import com.calculetor.utils.RefreshTokenRequest;
import com.calculetor.utils.RegisterRequest;
import com.calculetor.utils.UserDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;

    public AuthController(AuthService authService, RefreshTokenService refreshTokenService, JwtService jwtService) {
        this.authService = authService;
        this.refreshTokenService = refreshTokenService;
        this.jwtService = jwtService;
    }

    // @PostMapping("/register")
    // public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
    //     AuthResponse authResponse = authService.register(registerRequest);
    //     return ResponseEntity.ok(authResponse);
    // }

    // @PostMapping("/login")
    // public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
    //     AuthResponse authResponse = authService.login(loginRequest);
    //     return ResponseEntity.ok(authResponse);
    // }

   @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }
    // @PostMapping("/refresh")
    // public ResponseEntity<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
    //     RefreshToken refreshToken = refreshTokenService.verifyRefreshToken(refreshTokenRequest.getRefreshToken());
    //     User user = refreshToken.getUser();

    //     String accessToken = jwtService.generateToken(user);

    //     return ResponseEntity.ok(AuthResponse.builder()
    //             .accessToken(accessToken)
    //             .refreshToken(refreshToken.getRefreshToken())
    //             .build());
    // }
     public ResponseEntity<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        RefreshToken refreshToken = refreshTokenService.verifyRefreshToken(refreshTokenRequest.getRefreshToken());
        User user = refreshToken.getUser();

        String accessToken = jwtService.generateToken(user);

        return ResponseEntity.ok(AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getRefreshToken())
                .user(UserDto.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .status(user.getStatus())
                        .build()) // Include userDto in the response
                .build());
    }

}
