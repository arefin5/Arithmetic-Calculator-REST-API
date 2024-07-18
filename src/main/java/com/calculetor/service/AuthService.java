

package com.calculetor.service;

import com.calculetor.model.User;
import com.calculetor.repository.UserRepository;
import com.calculetor.utils.AuthResponse;
import com.calculetor.utils.LoginRequest;
import com.calculetor.utils.RegisterRequest;
import com.calculetor.utils.UserDto;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest registerRequest) {
        User user = User.builder()
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();

        User savedUser = userRepository.save(user);
        String accessToken = jwtService.generateToken(savedUser);
        String refreshToken = refreshTokenService.createRefreshToken(savedUser.getEmail()).getRefreshToken();

        UserDto userDto = UserDto.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .status("active") 
                .build();
        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .user(userDto) // Include userDto in the response
                .build();
    }

    public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        String accessToken = jwtService.generateToken(user);
        String refreshToken = refreshTokenService.createRefreshToken(loginRequest.getEmail()).getRefreshToken();

        UserDto userDto = UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .status("active") 
                .build();

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .user(userDto) // Include userDto in the response
                .build();
    }
}
