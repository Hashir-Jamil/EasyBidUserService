package com.EasyBid.UserService.service;

import com.EasyBid.UserService.model.Role;
import com.EasyBid.UserService.model.User;
import com.EasyBid.UserService.repository.UserRepository;
import com.EasyBid.UserService.web.dto.UserRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    @Lazy
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDTO userRegistrationDTO) {

        User user = new User(
                userRegistrationDTO.getEmail(),
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
    public User findByEmail(String email) throws Exception {
        List<User> users = userRepository.findAll();

        if(email == null) throw new Exception("Email not specified");
        
        if(users.isEmpty()) throw new Exception("No users registered");

        User output = null;

        for(int i = 0; i < users.size(); i++){
            //System.out.println("Name: " + temp.get(i).getName() + " Description: " + temp.get(i).getDescription());
            if(users.get(i).getEmail().contains(email))
                output = users.get(i);
        }

        if(output == null) throw new Exception("User not registered");

        return output;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("This username and password combination does not exist in our records");
        }
        user.setIsEnabled(true);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection <Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
