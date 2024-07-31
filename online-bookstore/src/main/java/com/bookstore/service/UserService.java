package com.bookstore.service;


import com.bookstore.entities.User;
import com.bookstore.entities.UserReview;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public UserReview createUser(User userRegistration);

//    String changePassword(ChangePassword password, int id);

}
