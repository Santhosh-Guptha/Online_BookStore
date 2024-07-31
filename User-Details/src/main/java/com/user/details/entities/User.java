package com.user.details.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "UserDetails")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "First name should not be blank")
    private String firstName;
    @NotBlank(message = "Middle name should not be blank")
    private String middleName;
    @NotBlank(message = "Last name should not be blank")
    private String lastName;
    @Column(unique = true,nullable = true)
    @Size(min = 10, max = 10, message = "Phone number size should be 10")
    private String phoneNumber;
    @Email(message = "Enter a valid email")
    private String email;
    @NotNull(message = "Username should not be null")
    private String username;
    private String roles;
    @NotNull(message = "Password should not be null")
    private String password;
}
