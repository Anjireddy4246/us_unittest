package com.unittest.user.service.v1;

import com.unittest.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();
    Optional<User> getById();
}