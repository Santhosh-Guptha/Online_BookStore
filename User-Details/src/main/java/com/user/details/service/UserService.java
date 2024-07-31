package com.user.details.service;

import com.user.details.entities.ChangePassword;
import com.user.details.entities.User;
import com.user.details.entities.UserReview;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    String  deleteUser(int id);

    UserReview getUserById(int id);

    UserReview  updateUserById(int id, UserReview userReview);

    String changePassword(ChangePassword password, int id);

    List<User> getAllUsers();

    String sendMail(int userId,String items);
}
