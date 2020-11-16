package com.example.voting.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@RestController
public class GoogleServiceController {

    @RequestMapping(value = "/user")
    public Principal user(Principal principal) {
        return principal;
    }

    @RequestMapping("/user1")
    public String home(Principal user, HttpServletRequest request, HttpServletResponse response) throws ServletException {
        return "Hello " + user.getName();
    }
}
