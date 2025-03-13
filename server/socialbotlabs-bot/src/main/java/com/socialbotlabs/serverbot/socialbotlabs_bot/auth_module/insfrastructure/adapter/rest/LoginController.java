package com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.insfrastructure.adapter.rest;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/logout")
    public String logout(){
        return "logout";
    }

    @PostMapping("/logout")
    public String  logoutOk(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.logout(httpSecurityLogoutConfigurer ->
            httpSecurityLogoutConfigurer.logoutSuccessUrl("login?logout")
                .deleteCookies("JSESSIONID")
                .clearAuthentication(true)
                .invalidateHttpSession(true));
        return "login?logout";
    }
}
