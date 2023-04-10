package com.EasyBid.UserService.security.configuration;

import com.EasyBid.UserService.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;
    
    private static final CustomAuthenticationSuccessHandler instance = new CustomAuthenticationSuccessHandler();

    private CustomAuthenticationSuccessHandler(){}

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        //System.out.println("Request ItemList1");
        String username = request.getParameter("username");
        request.getSession().setAttribute("username", username);

        long id;

        try {
            id = userService.findByEmail(username).getId();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String target = "http://localhost:8090/auction/" + id;//"http://localhost:3000/" + uName;//
        //request.getRequestDispatcher(target).forward(request, response);
        response.sendRedirect(target);
        //System.out.println("Request ItemList2");
    }

    public static CustomAuthenticationSuccessHandler getInstance() {
        return instance;
    }
}