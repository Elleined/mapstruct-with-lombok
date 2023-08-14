package com.elleined.mapstructwithlombok.service;

import com.elleined.mapstructwithlombok.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public int getBookCount(User user) {
        return user.getBooks().size();
    }
}
