package com.EasyBid.UserService.service;

import com.EasyBid.UserService.model.User;
import com.EasyBid.UserService.repository.UserRepository;
import com.EasyBid.UserService.web.dto.UserRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDTO userRegistrationDTO) {

        User user = new User(userRegistrationDTO.getEmail(),
                bCryptPasswordEncoder.encode(userRegistrationDTO.getPassword()),
                userRegistrationDTO.getFirstName(),
                userRegistrationDTO.getLastName(),
                userRegistrationDTO.getDob(),
                userRegistrationDTO.getPhoneNumber(),
                userRegistrationDTO.getAddress()
                );

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("This username and password combination does not exist in our records");
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), null);

    }

}
