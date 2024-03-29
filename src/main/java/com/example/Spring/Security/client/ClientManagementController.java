package com.example.Spring.Security.client;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/clients")
public class ClientManagementController {

    private static final List<Client> CLIENTS = Arrays.asList(
            new Client(1, "Darius"),
            new Client(2, "Daniel"),
            new Client(3, "Alex")
    );

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public List<Client> getAllClients() {
        System.out.println("getAllClients");
        return CLIENTS;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('client:write')")
    public void registerNewClient(@RequestBody Client client){
        System.out.println("registerNewClient");
        System.out.println(client);
    }

    @DeleteMapping(path = "{clientId}")
    @PreAuthorize("hasAuthority('client:write')")
    public void deleteClient (@PathVariable("clientId") Integer clientId){
        System.out.println("deleteClient");
        System.out.println(clientId);
    }

    @PutMapping(path = "{clientId}")
    @PreAuthorize("hasAuthority('client:write')")
    public void updateClient(@PathVariable("clientId") Integer clientId, @RequestBody Client client){
        System.out.println("updateClient");
        System.out.println(String.format("%s %s", clientId, client));
    }

}
