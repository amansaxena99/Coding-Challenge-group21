package com.team21.SocialDesking.services;

import com.team21.SocialDesking.entities.User;

public interface UserService {
    User findUserByEmail(String email);
    User getUserById(int id);
}
