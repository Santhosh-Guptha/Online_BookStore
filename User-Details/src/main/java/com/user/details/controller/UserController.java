package com.user.details.controller;

import com.user.details.entities.ChangePassword;
import com.user.details.entities.User;
import com.user.details.entities.UserReview;
import com.user.details.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<UserReview> getUserById(@PathVariable int id){
        return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
    }

    @PutMapping("/updatebyid/{id}")
    public ResponseEntity<UserReview> updateUserById(@PathVariable int id, @RequestBody UserReview userReview){
        return new ResponseEntity<>(userService.updateUserById(id,userReview),HttpStatus.OK);
    }

    @PutMapping("/updatepassword/{id}")
    public ResponseEntity<String> updatePassword(@RequestBody ChangePassword password, @PathVariable int id){
        return new ResponseEntity<>(userService.changePassword(password,id),HttpStatus.OK);
    }

    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id){
        return new ResponseEntity<>(userService.deleteUser(id),HttpStatus.OK);
    }

    @GetMapping("/getallusers")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

    @PostMapping("/send/{userId}/{message}/{items}")
    public String sendMail(@PathVariable int userId,@PathVariable String message, @PathVariable String items){
        return userService.sendMail(userId,message,items);
    }
}
