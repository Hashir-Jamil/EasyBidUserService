package com.EasyBid.UserService.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

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

}
