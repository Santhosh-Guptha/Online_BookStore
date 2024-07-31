package com.bookstore.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReview {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String username;
    private String roles;
    private String password;
}
