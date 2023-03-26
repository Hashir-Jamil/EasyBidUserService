package com.EasyBid.UserService.service;

import com.EasyBid.UserService.model.User;
import com.EasyBid.UserService.repository.UserRepository;
import com.EasyBid.UserService.web.dto.UserRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDTO userRegistrationDTO) {

        User user = new User(userRegistrationDTO.getEmail(),
                userRegistrationDTO.getPassword(),
                userRegistrationDTO.getFirstName(),
                userRegistrationDTO.getLastName(),
                userRegistrationDTO.getDob(),
                userRegistrationDTO.getPhoneNumber(),
                userRegistrationDTO.getAddress()
                );

        return userRepository.save(user);
    }
}
