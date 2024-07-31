package com.user.details.serviceimpl;

import com.user.details.entities.ChangePassword;
import com.user.details.entities.User;
import com.user.details.entities.UserReview;
import com.user.details.exception.UserNotFoundException;
import com.user.details.repository.UserRepository;
import com.user.details.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private WebClient webClient;

    @Autowired
    private JavaMailSender sender;

    @Override
    public UserReview getUserById(int id) {
        User user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User Not Found by the given id."));
        UserReview userDto = modelMapper.map(user, UserReview.class);
        return userDto;
    }

    @Override
    public UserReview updateUserById(int id, UserReview userDto) {
        User oldUser = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User Not Found by the given id."));
        User newUser = modelMapper.map(userDto,User.class);
        if(oldUser != null) {
            if(StringUtils.hasText(newUser.getUsername())) {
                oldUser.setUsername(newUser.getUsername());
            }
            if(StringUtils.hasText(newUser.getEmail())) {
                oldUser.setEmail(newUser.getEmail());
            }
            if(StringUtils.hasText(newUser.getFirstName())) {
                oldUser.setFirstName(newUser.getFirstName());
            }
            if(StringUtils.hasText(newUser.getMiddleName())) {
                oldUser.setMiddleName(newUser.getMiddleName());
            }
            if(StringUtils.hasText(newUser.getPhoneNumber())) {
                oldUser.setPhoneNumber(newUser.getPhoneNumber());
            }
            if(StringUtils.hasText(newUser.getLastName())){
                oldUser.setLastName(newUser.getLastName());
            }
            User user = userRepository.save(oldUser);
            return modelMapper.map(user,UserReview.class);
        }
        else {
            throw new RuntimeException("User Not Updated");
        }
    }

    @Override
    public String changePassword(ChangePassword password, int id) {
        User user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User Not Found by the given id."));
        if(!passwordEncoder.matches(password.getOldPassword(),user.getPassword())){
            return "Invalid Credentials";
        }
        else {
            user.setPassword(passwordEncoder.encode(password.getNewPassword()));
            userRepository.save(user);
            return "Password Successfully changed.";
        }
    }

    @Override
    public String deleteUser(int id) {
        User user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User Not Found by the given id."));
        if(user != null){
            userRepository.delete(user);
            return "Successfully Deleted";
        }
        return "User not Found";
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public String sendMail(int userId, String message, String items ) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return "User not found";
        }

        String emailMessage = "Dear " + user.getUsername() + ",\n\n"
                + "We are pleased to inform you that your order will be delivered on " + message + ".\n\n"
                + "Items:\n"
                + items + "\n\n"
                + "Thank you for choosing us for your order.\n\n"
                + "Sincerely,\n"
                + "[Santhosh]";

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("Order Placed");
        simpleMailMessage.setText(emailMessage);
        simpleMailMessage.setFrom("santhoshbukka5@gmail.com");
        simpleMailMessage.setTo(user.getEmail());
        sender.send(simpleMailMessage);
        return "Successfull mail sent";
       // "Dear "+user.getUsername()+",Your Order will be delivered on "+message+" With Items "+items+" Thank you For the order "
    }

}
