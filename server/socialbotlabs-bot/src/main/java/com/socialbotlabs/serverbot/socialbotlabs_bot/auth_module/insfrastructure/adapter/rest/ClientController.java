package com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.insfrastructure.adapter.rest;

import com.socialbotlabs.serverbot.socialbotlabs_bot.client_module.application.dto.ClientRequest;
import com.socialbotlabs.serverbot.socialbotlabs_bot.client_module.application.dto.ClientResponse;
import com.socialbotlabs.serverbot.socialbotlabs_bot.client_module.application.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping(value = "/registered-client",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientResponse> createClient(@RequestBody ClientRequest clientRequest){
        Optional<ClientResponse> response = clientService.createClient(clientRequest);
        return response.map(
            clientResponse -> ResponseEntity.status(HttpStatus.CREATED)
                .body(clientResponse))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }
}
