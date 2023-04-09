package com.EasyBid.UserService.web.controller;

import com.EasyBid.UserService.model.User;
import com.EasyBid.UserService.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class MainController {

    private UserRepository userRepository; // assuming UserRepository is your repository interface for user entity
    @Autowired
    public MainController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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

    @PostMapping("/userTransfer")
    public Optional<User> transferUserJSON(@RequestBody String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    @GetMapping("/userNameTransfer/{id}")
    @ResponseBody //this is here because Thymeleaf autodirects strings to a template
    public String trasnferNameFromId(@PathVariable("id") Long id) {
        System.out.println("test");
        String name = "";
        Optional<User> user;
        user = userRepository.findById(id);
        name = user.get().getFirstName() + " " + user.get().getLastName();
        return name;
    }
}
