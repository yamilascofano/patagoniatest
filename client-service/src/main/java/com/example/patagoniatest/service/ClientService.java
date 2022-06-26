package com.example.patagoniatest.service;

import com.example.patagoniatest.entity.Client;
import com.example.patagoniatest.feignclients.LoanFeignClient;
import com.example.patagoniatest.model.Loan;
import com.example.patagoniatest.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    LoanFeignClient loanFeignClient;

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new IllegalStateException("No existe prestamo "));
        clientRepository.delete(client);
    }

    @Transactional
    public void update(Long id, Client client) throws IllegalStateException {
        Client client1 = new Client();
        client1.setId(id);
        if (client1 == null)
            throw new IllegalStateException("El cliente no existe");
        if (!client1.getIncome().equals(client.getIncome()))
            client1.setIncome(client.getIncome());
        if (!client1.getFullName().equals(client.getFullName()))
            client1.setFullName(client.getFullName());
        clientRepository.save(client1);

    }

    public Client findById(Long id) throws Exception {
        return clientRepository.findById(id).orElseThrow(() -> new Exception("No existe " + id));
    }

    public Loan saveLoan(Long clientId, Loan loan) throws Exception {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new Exception("No existe cliente " + clientId));
        if (client != null)
            loan.setClientId(clientId);
        return loanFeignClient.saveLoan(loan);
    }
}
