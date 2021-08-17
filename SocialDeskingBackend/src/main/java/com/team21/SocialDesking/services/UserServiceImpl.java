package com.team21.SocialDesking.services;

import com.team21.SocialDesking.entities.User;
import com.team21.SocialDesking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id);
    }
}
