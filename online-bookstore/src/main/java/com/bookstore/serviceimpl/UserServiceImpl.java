package com.bookstore.serviceimpl;

import com.bookstore.entities.ShoppingCart;
import com.bookstore.entities.User;
import com.bookstore.entities.UserReview;
import com.bookstore.repository.UserRepository;
import com.bookstore.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private WebClient webClient;
    @Override
    public UserReview createUser(User user)
    {
        if(user.getUsername().matches("santhosh@18")) {
            user.setRoles("ROLE_ADMIN,ROLE_USER");
        }else{
            user.setRoles("ROLE_USER");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserReview userReview = modelMapper.map(userRepository.save(user),UserReview.class);
        createCart(userReview.getId());
        return userReview;

    }

    private void createCart(int userId) {
        ShoppingCart cart = webClient.post()
                .uri("http://localhost:8083/cart/{userId}", userId)
                .retrieve()
                .bodyToMono(ShoppingCart.class)
                .block();
    }
}
