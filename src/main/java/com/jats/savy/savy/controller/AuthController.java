package com.jats.savy.savy.controller;

import com.jats.savy.savy.payload.request.AuthRequest;
import com.jats.savy.savy.payload.response.AuthResponse;
import com.jats.savy.savy.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AuthResponse createAccessToken(@RequestBody @Validated AuthRequest authRequest) {
        return authService.createToken(authRequest);
    }

}
