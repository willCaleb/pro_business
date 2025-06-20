package com.will.caleb.business.controller;

import com.will.caleb.business.model.records.requests.AuthRequest;
import com.will.caleb.business.model.records.requests.RegisterRequest;
import com.will.caleb.business.model.records.responses.AuthResponse;
import com.will.caleb.business.service.impl.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AuthController.PATH)
@RequiredArgsConstructor
public class AuthController extends AbstractController{

    public static final String PATH = "${api.prefix.v1}/auth";

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}