package com.example.patagoniatest.controller;

import com.example.patagoniatest.entity.Client;
import com.example.patagoniatest.model.Loan;
import com.example.patagoniatest.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private final ClientService clientService;
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getClients() {
        return clientService.getClients();
    }

    @GetMapping("/findById/{id}")
    public Client findById(@PathVariable Long id) throws Exception {
        return clientService.findById(id);
    }

    @PostMapping("/addClient")
    public Client addClient(@RequestBody Client client) {
        return clientService.addClient(client);
    }

    @PutMapping("/putClient/{id}")
    public void putClient(@PathVariable Long id, @RequestBody Client client) throws IllegalStateException {
        clientService.update(id, client);
    }

    @DeleteMapping("/deleteClient/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }


    @PostMapping("/addLoan/{clientId}")
    public Loan saveLoan(@PathVariable("clientId") Long clientId, @RequestBody Loan loan) throws Exception {
        return clientService.saveLoan(clientId, loan);
    }
}
