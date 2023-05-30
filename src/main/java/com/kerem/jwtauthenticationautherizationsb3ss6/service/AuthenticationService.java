package com.kerem.jwtauthenticationautherizationsb3ss6.service;

import com.kerem.jwtauthenticationautherizationsb3ss6.dao.request.SignInRequest;
import com.kerem.jwtauthenticationautherizationsb3ss6.dao.request.SignUpRequest;
import com.kerem.jwtauthenticationautherizationsb3ss6.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SignInRequest request);
}
