/*
package com.EasyBid.UserService.controller;

import com.EasyBid.UserService.model.User;
import com.EasyBid.UserService.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Controller
public class RegistrationController {
    static int databaseID = 0;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/http://localhost:8080/registerUser/{userName}/{password}/{name}/{dob}/{phone}/{address}")
    public String registerUserGet(@PathVariable("userName") String userName,
                               @PathVariable("password") String password,
                               @PathVariable("name") String name,
                               @PathVariable("dob") String dob,
                               @PathVariable("phone") String phone,
                               @PathVariable("address") String address) {
        User user = new User();
        LocalDate localDate = LocalDate.parse(dob);
        databaseID++;
        user.setId(databaseID);
        user.setUserID(userName);
        user.setPassword(password);
        user.setName(name);
        user.setDob(localDate);
        user.setPhoneNumber(phone);
        user.setAddress(address);
        userRepository.save(user);
        return "login";
    }
}
*/
