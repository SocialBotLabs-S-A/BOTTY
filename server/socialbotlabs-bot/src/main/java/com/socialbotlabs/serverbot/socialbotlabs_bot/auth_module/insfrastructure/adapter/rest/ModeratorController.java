package com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.insfrastructure.adapter.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// Controller for moderator endpoints
@RestController
@RequestMapping("/v1/moderator")
@PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
public class ModeratorController {

    @GetMapping("/dashboard")
    public ResponseEntity<String> getModeratorDashboard() {
        return ResponseEntity.ok("Welcome to the Moderator Dashboard.");
    }
}
