package com.EasyBid.UserService.controller;

import com.EasyBid.UserService.model.User;
import com.EasyBid.UserService.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Controller
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    static int databaseID = 0;

    RestTemplate restTemplate = new RestTemplate();
    @RequestMapping("/")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/login")
    public String homePage(@RequestParam("userName") String userName,
                           @RequestParam("password") String password, Model model) {

        User user = null;
        try {
            user = userRepository.findByName(userName);
        } catch (Exception e) {
            System.out.println("User not found in our records. Please consider Registration");
        }
        if(user != null) {
            model.addAttribute("UserName", userName);
            return "userHome";
        }
        model.addAttribute("errorUserNotFound", "The user was not found in our records. Please consider Registration.");
        return "login";
    }

    @RequestMapping("/register")
    public String goToRegisterPage() {
        return "register";
    }

    @RequestMapping("/backLogin")
    public String goToLoginPage() {
        return "login";
    }

    @RequestMapping(value = "/registerUser")
    public String registerUserSend(@RequestParam("userName") String userName,
                               @RequestParam("password1") String password1,
                               @RequestParam("password2") String password2,
                               @RequestParam("name") String name,
                               @RequestParam("dob") String dob,
                               @RequestParam("phoneNum") String phoneNum,
                               @RequestParam("address") String address,
                               Model model) {
        if (password1.equals(password2)) {
            restTemplate.getForObject("http://localhost:8080/registerUser?userName={userName}&password={password}&name={name}&dob={dob}&phone={phone}&address={address}",
                    String.class, userName, password1, name, dob, phoneNum, address);
            // restTemplate.getForObject("http://localhost:8080/register-User/{"+userName+"}/{"+password1+"}/{"+name+"}/{"+dob+"}/{"+phoneNum+"}/{"+address+"}", String.class);
            model.addAttribute("registrationSuccess", "Successfully registered to EasyBid. Please proceed to login.");
        }
        else {
            model.addAttribute("registrationError", "Password does not match.");
        }
        return "login";
    }

    @RequestMapping(value = "/http://localhost:8080/registerUser/{userName}/{password}/{name}/{dob}/{phone}/{address}", method = RequestMethod.GET)
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
