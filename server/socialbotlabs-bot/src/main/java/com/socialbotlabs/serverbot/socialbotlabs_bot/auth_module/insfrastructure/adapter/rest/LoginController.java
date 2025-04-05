package com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.insfrastructure.adapter.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/logout-confirm")
    public String logout(){
        return "logout";
    }

}
