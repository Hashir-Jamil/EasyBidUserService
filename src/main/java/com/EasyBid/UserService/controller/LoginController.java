package com.EasyBid.UserService.controller;

import com.EasyBid.UserService.model.User;
import com.EasyBid.UserService.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class LoginController {
    @Autowired
    private UserRepository userRepository;

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

    @RequestMapping("/registerUser")
    public String registerUserSend(@RequestParam("userName") String userName,
                               @RequestParam("password1") String password1,
                               @RequestParam("password2") String password2,
                               @RequestParam("name") String name,
                               @RequestParam("dob") String dob,
                               @RequestParam("phoneNum") String phoneNum,
                               @RequestParam("address") String address,
                               Model model) {
        if (password1.equals(password2)) {
            restTemplate.getForObject("http://localhost:8080/register-User?userName={userName}&password={password}&name={name}&dob={dob}&phone={phone}&address={address}",
                    String.class, userName, password1, name, dob, phoneNum, address);
            // restTemplate.getForObject("http://localhost:8080/register-User/{"+userName+"}/{"+password1+"}/{"+name+"}/{"+dob+"}/{"+phoneNum+"}/{"+address+"}", String.class);
            model.addAttribute("registrationSuccess", "Successfully registered to EasyBid. Please proceed to login.");
        }
        else {
            model.addAttribute("registrationError", "Password does not match.");
        }
        return "login";
    }
}
