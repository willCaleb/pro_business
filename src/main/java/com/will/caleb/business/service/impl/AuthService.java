package com.will.caleb.business.service.impl;

import com.will.caleb.business.context.TenantContext;
import com.will.caleb.business.model.entity.Enterprise;
import com.will.caleb.business.model.entity.User;
import com.will.caleb.business.model.records.converters.EnterpriseMapper;
import com.will.caleb.business.model.records.requests.AuthRequest;
import com.will.caleb.business.model.records.requests.RegisterRequest;
import com.will.caleb.business.model.records.responses.AuthResponse;
import com.will.caleb.business.repository.UserRepository;
import com.will.caleb.business.service.EnterpriseService;
import com.will.caleb.business.service.UserService;
import com.will.caleb.business.utils.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authManager;
    private final EnterpriseService enterpriseService;
    private final EnterpriseMapper enterpriseMapper;

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .build();

        Enterprise enterprise = enterpriseService.include(enterpriseMapper.toEntity(request.enterprise()));

        TenantContext.setEnterprise(enterprise);

        user = userService.include(user);
        String jwt = jwtUtil.generateToken(user);
        return new AuthResponse(user.getUsername(), jwt);
    }

    public AuthResponse login(AuthRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(), request.password())
        );

        User user = userService.findByUsername(request.username()).orElseThrow();

        Enterprise enterprise = enterpriseService.findById(user.getEnterprise());

        TenantContext.setEnterprise(enterprise);

        String jwt = jwtUtil.generateToken(user);
        return new AuthResponse(user.getUsername(), jwt);
    }
}