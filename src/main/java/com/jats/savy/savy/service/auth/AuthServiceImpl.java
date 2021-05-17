package com.jats.savy.savy.service.auth;

import com.jats.savy.savy.entity.admin.Admin;
import com.jats.savy.savy.entity.admin.AdminRepository;
import com.jats.savy.savy.exception.UserNotFoundException;
import com.jats.savy.savy.payload.request.AuthRequest;
import com.jats.savy.savy.payload.response.AuthResponse;
import com.jats.savy.savy.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final AdminRepository adminRepository;


    @Override
    public AuthResponse createToken(AuthRequest request) {
        return adminRepository.findById(request.getCode())
                .map(Admin::getCode)
                .map(jwtTokenProvider::generateAccessToken)
                .map(AuthResponse::new)
                .orElseThrow(UserNotFoundException::new);
    }
}
