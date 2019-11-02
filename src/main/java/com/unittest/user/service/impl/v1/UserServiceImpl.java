package com.unittest.user.service.impl.v1;

import com.unittest.user.entity.User;
import com.unittest.user.repository.v1.UserRepository;
import com.unittest.user.service.v1.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        Iterable<User> it = userRepository.findAll();
        List<User> users = new ArrayList<>();
        it.forEach(e -> users.add(e));
        return users;
    }

    @Override
    public Optional<User> getById() {
      return  userRepository.findById(1L);
    }
}
