package com.user.details.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChangePassword {
    private String oldPassword;
    private String confirmPassword;
    private String newPassword;
}
