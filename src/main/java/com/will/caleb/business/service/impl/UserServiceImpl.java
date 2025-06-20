package com.will.caleb.business.service.impl;

import com.will.caleb.business.model.entity.User;
import com.will.caleb.business.model.records.requests.RegisterRequest;
import com.will.caleb.business.repository.UserRepository;
import com.will.caleb.business.service.UserService;
import com.will.caleb.business.validator.UserValidator;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private UserValidator userValidator = new UserValidator();

    @PostConstruct
    private void setUserValidatorRepository() {
        userValidator.setUserRepository(userRepository);
    }

    @Override
    public User register(RegisterRequest request) {

        User user = User.builder()
                .username(request.username())
                .password(request.password())
                .build();

        userValidator.validateAvailableUsername(user);
        userValidator.validate(user);

        return userRepository.save(user);
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User include(User user) {

        userValidator.validateAvailableUsername(user);
        userValidator.validate(user);

        return userRepository.save(user);
    }
}
