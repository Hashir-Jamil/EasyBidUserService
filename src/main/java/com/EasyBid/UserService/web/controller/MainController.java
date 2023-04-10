package com.EasyBid.UserService.web.controller;

import com.EasyBid.UserService.model.User;
import com.EasyBid.UserService.repository.UserRepository;
import com.EasyBid.UserService.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private UserService userService; // assuming UserRepository is your repository interface for user entity
//    @Autowired
//    public MainController(UserService userServie) {
//        this.userService = userService;
//    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

//    @PostMapping("/login")
//    public void login(HttpServletRequest request, HttpServletResponse response){
//        String uName = request.getParameter("username");
//        request.getSession().setAttribute("username", uName);
//    }

    @GetMapping("/")
    public String home(HttpServletResponse response) {
        //redirecting to the home with all the auctions
        //response.setHeader("Location", "localhost:8090/?id="+1);
        //response.setStatus(302);
        return "home";
        //return "redirect:localhost:8090/?id="+1;
    }

//    @GetMapping("/userTransfer/{email}")
//    @ResponseBody
//    public User transferUserJSON(@PathVariable("email") String email) throws Exception {
//        User u = userService.findByEmail(email);
//        System.out.println("User: " + u.toString());
//        return u;
//    }

    @GetMapping("/firstLastTransfer/{id}")
    @ResponseBody //this is here because Thymeleaf autodirects strings to a template
    public String firstLastTransferFromId(@PathVariable("id") Long id) {
        System.out.println("test");
        String out = "";
        User user = userService.findById(id);
        out = user.getFirstName() + " " + user.getLastName();
        return out;
    }

    @GetMapping("/emailTransfer/{id}")
    @ResponseBody //this is here because Thymeleaf autodirects strings to a template
    public String emailTransferFromId(@PathVariable("id") Long id) {
        System.out.println("test");
        String out = "";
        User user = userService.findById(id);
        out = user.getEmail();
        return out;
    }

    @GetMapping("/dobTransfer/{id}")
    @ResponseBody //this is here because Thymeleaf autodirects strings to a template
    public String dobTransferFromId(@PathVariable("id") Long id) {
        System.out.println("test");
        String out = "";
        User user = userService.findById(id);
        out = user.getDob();
        return out;
    }

    @GetMapping("/phoneTransfer/{id}")
    @ResponseBody //this is here because Thymeleaf autodirects strings to a template
    public String phoneTransferFromId(@PathVariable("id") Long id) {
        System.out.println("test");
        String out = "";
        User user = userService.findById(id);
        out = user.getPhoneNumber();
        return out;
    }

    @GetMapping("/addressTransfer/{id}")
    @ResponseBody //this is here because Thymeleaf autodirects strings to a template
    public String addressTransferFromId(@PathVariable("id") Long id) {
        System.out.println("test");
        String out = "";
        User user = userService.findById(id);
        out = user.getAddress();
        return out;
    }
}
