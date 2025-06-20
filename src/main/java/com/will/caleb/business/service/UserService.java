package com.will.caleb.business.service;

import com.will.caleb.business.model.entity.User;
import com.will.caleb.business.model.records.requests.RegisterRequest;

import java.util.Optional;

public interface UserService {

    User register(RegisterRequest request);

    User findById(Integer id);

    Optional<User> findByUsername(String username);

    User include(User user);

}
