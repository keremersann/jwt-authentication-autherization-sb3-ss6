package com.kerem.jwtauthenticationautherizationsb3ss6.service.impl;

import com.kerem.jwtauthenticationautherizationsb3ss6.dao.request.SignInRequest;
import com.kerem.jwtauthenticationautherizationsb3ss6.dao.request.SignUpRequest;
import com.kerem.jwtauthenticationautherizationsb3ss6.dao.response.JwtAuthenticationResponse;
import com.kerem.jwtauthenticationautherizationsb3ss6.entity.Role;
import com.kerem.jwtauthenticationautherizationsb3ss6.entity.User;
import com.kerem.jwtauthenticationautherizationsb3ss6.repository.UserRepository;
import com.kerem.jwtauthenticationautherizationsb3ss6.service.AuthenticationService;
import com.kerem.jwtauthenticationautherizationsb3ss6.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = User.builder().firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
