package com.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.web.entity.User;
import com.web.repository.UserRepository;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
public class    UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void register(User user){
        if (user.getEmail() == null || user.getEmail().isBlank()){
            throw new IllegalArgumentException("email is empty!!!");
        }
        if (user.getName() == null || user.getName().isBlank()){
            throw new IllegalArgumentException("name is empty!!!");
        }
        if (user.getPassword() == null || user.getPassword().length() < 6) {
            throw new IllegalArgumentException("password must have at list 6 symbol");
        }
        if (userRepository.existsByEmailIgnoreCase(user.getEmail())) {
            throw new IllegalArgumentException("same email already exist");
        }
//        user.setPassword(user.getPassword());
        userRepository.save(user);

    }
    public User logginig(User user, String password){

        if (user.getEmail() == null || user.getEmail().isBlank()){

            throw new IllegalArgumentException("email is empty!!!");
        }
        if (user.getPassword() == null || user.getPassword().length() < 6) {
            throw new IllegalArgumentException("password must have at list 6 symbol");
        }
        User foundUser = userRepository.findByEmailIgnoreCase(user.getEmail()).orElseThrow(() -> new  IllegalArgumentException("User not found"));

        if(!password.equals(foundUser.getPassword())){
            throw new IllegalArgumentException("Wrong password");
        }
        return foundUser;


    }




}
