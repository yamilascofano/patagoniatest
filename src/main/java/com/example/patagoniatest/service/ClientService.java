package com.example.patagoniatest.service;

import com.example.patagoniatest.model.Client;
import com.example.patagoniatest.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, Client client) throws IllegalStateException {
        Client client1 = new Client();
        client1.setId(id);
        if (client1==null)
            throw new IllegalStateException("El cliente no existe");
        if (!client1.getIncome().equals(client.getIncome()))
        client1.setIncome(client.getIncome());
        if (!client1.getFullName().equals(client.getFullName()))
        client1.setFullName(client.getFullName());
        clientRepository.save(client1);

    }

    public Client findById(Long id) throws Exception {
        Optional<Client> optClient = clientRepository.findById(id);
        Client client = optClient.get();
        if (optClient.isEmpty())
        new Exception("No existe Cliente");
        return client;
    }
}
