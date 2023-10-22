package org.example.services;

import org.example.entities.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private static final String INSERT_NEW = "INSERT INTO client (name) VALUES(?)";
    private static final String SELECT_NAME_BY_ID = "SELECT name FROM client WHERE id = ?";
    private static final String UPDATE_NAME_BY_ID = "UPDATE client SET name = ? WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM client WHERE id = ?";
    private static final String SELECT_ALL = "SELECT id, name FROM client";
    private static final String SELECT_LAST = "SELECT id FROM client ORDER BY id DESC LIMIT 1";
    private Connection connection;
    private PreparedStatement insertStatement;
    private PreparedStatement selectLastStatement;
    private PreparedStatement selectByIdStatement;
    private PreparedStatement updateByIdStatement;
    private PreparedStatement deleteByIdStatement;
    private PreparedStatement selectAllStatement;

    public ClientService(Connection connection) {
        this.connection = connection;
        try {
            this.insertStatement = connection.prepareStatement(INSERT_NEW);
            this.selectLastStatement = connection.prepareStatement(SELECT_LAST);
            this.selectByIdStatement = connection.prepareStatement(SELECT_NAME_BY_ID);
            this.updateByIdStatement = connection.prepareStatement(UPDATE_NAME_BY_ID);
            this.deleteByIdStatement = connection.prepareStatement(DELETE_BY_ID);
            this.selectAllStatement = connection.prepareStatement(SELECT_ALL);
        } catch (SQLException e) {
            System.out.println("Client Service construction exception. Reason: " + e.getMessage());
        }
    }

    public long createNewClient(String name) {
        try {
            if (name.length() < 2 || name.length() > 1000) {
                throw new IllegalArgumentException("Incorrect name length");
            }
            try {
                this.insertStatement.setString(1, name);
                this.insertStatement.executeUpdate();
                try (ResultSet resultSet = this.selectLastStatement.executeQuery()) {
                    if (resultSet.next()) {
                        long id = resultSet.getLong("id");
                        Client client = new Client(id, name);
                        return client.getId();
                    }
                } catch (SQLException e) {
                    System.out.println("Select Client exception. Reason: " + e.getMessage());
                }
            } catch (SQLException e) {
                System.out.println("Insert new Client exception. Reason: " + e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public String findClientById(long id) {
        try {
            if (id <= 0) {
                throw new IllegalArgumentException("Incorrect id value");
            }
            try {
                this.selectByIdStatement.setLong(1, id);
                try (ResultSet resultSet = this.selectByIdStatement.executeQuery()) {
                    if (resultSet.next()) {
                        Client client = new Client(id, resultSet.getString("name"));
                        return client.getName();
                    }
                } catch (SQLException e) {
                    System.out.println("Select Client exception. Reason: " + e.getMessage());
                }
            } catch (SQLException e) {
                System.out.println("Select Client exception. Reason: " + e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void setNameClientById(String name, long id) {
        try {
            if (name.length() < 2 || name.length() > 1000 || id <= 0) {
                throw new IllegalArgumentException("Incorrect input data");
            }
            try {
                this.updateByIdStatement.setString(1, name);
                this.updateByIdStatement.setLong(2, id);
                this.updateByIdStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Update Client exception. Reason: " + e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteClientById(long id) {
        try {
            if (id <= 0) {
                throw new IllegalArgumentException("Incorrect id value");
            }
            try {
                this.deleteByIdStatement.setLong(1, id);
                this.deleteByIdStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Delete Client exception. Reason: " + e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Client> findAllClient() {
        List<Client> clients = new ArrayList<>();
        try (ResultSet resultSet = this.selectAllStatement.executeQuery()) {
            while (resultSet.next()) {
                Client client = new Client(resultSet.getLong("id"), resultSet.getString("name"));
                clients.add(client);
            }
        } catch (SQLException e) {
            System.out.println("Insert new Client exception. Reason: " + e.getMessage());
        }
        return clients;
    }
}
