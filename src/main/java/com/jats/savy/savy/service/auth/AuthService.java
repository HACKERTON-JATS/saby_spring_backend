package com.jats.savy.savy.service.auth;

import com.jats.savy.savy.payload.request.AuthRequest;
import com.jats.savy.savy.payload.response.AuthResponse;

public interface AuthService {
    AuthResponse createToken(AuthRequest request);
}
