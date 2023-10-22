package org.example;

import org.example.db.*;
import org.example.entities.*;
import org.example.services.ClientService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Database database = Database.getInstance();

        ClientService clientService = new ClientService(database.getConnection());

        //insert new client
        String newClient = "Tri Star Pictures";
        long createdClientId = clientService.createNewClient(newClient);
        System.out.println("new client id = " + createdClientId);

        //select client name by id
        long clientId = 5L;
        String clientName = clientService.findClientById(clientId);
        System.out.println("client id = " + clientId + ", name = " + clientName);

        //update client name by id
        clientService.setNameClientById("New name", clientId);

        //delete client name by id
        clientId = 8L;
        clientService.deleteClientById(clientId);

        //select all clients
        List<Client> allClient = clientService.findAllClient();
        for (Client client : allClient) {
            System.out.println("client = " + client);
        }

        database.closeConnection();
    }
}