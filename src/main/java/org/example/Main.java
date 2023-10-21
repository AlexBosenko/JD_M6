package org.example;

import org.example.db.*;
import org.example.entities.*;

import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Database database = Database.getInstance();

        //init DB
        new DatabaseInitService().initDb(database);

        //populate DB
        DatabasePopulateService dps = new DatabasePopulateService();
        //simple statement
        //dps.populateDb(database);

        //prepared statement
        dps.populateWorker(database);
        dps.populateClient(database);
        dps.populateProject(database);
        dps.populateProjectWorker(database);

        //queries
        System.out.println("Workers with MAX salary:");
        List<MaxSalaryWorker> maxSalaryWorkers = new DatabaseQueryService().findMaxSalaryWorker(database);
        for (MaxSalaryWorker maxSalaryWorker : maxSalaryWorkers) {
            System.out.println(maxSalaryWorker);
        }

        System.out.println("----------------------");

        System.out.println("Clients project count:");
        List<MaxProjectCountClient> maxProjectCountClient = new DatabaseQueryService().findMaxProjectsClient(database);
        for (MaxProjectCountClient projectCountClient : maxProjectCountClient) {
            System.out.println(projectCountClient);
        }

        System.out.println("----------------------");

        System.out.println("Longest project:");
        List<LongestProject> longestProjects = new DatabaseQueryService().findLongestProject(database);
        for (LongestProject longestProject : longestProjects) {
            System.out.println(longestProject);
        }

        System.out.println("----------------------");

        System.out.println("Youngest eldest workers:");
        List<YoungestEldestWorkers> youngestEldestWorkers = new DatabaseQueryService().findYoungestEldestWorkers(database);
        for (YoungestEldestWorkers youngestEldestWorker : youngestEldestWorkers) {
            System.out.println(youngestEldestWorker);
        }

        System.out.println("----------------------");

        System.out.println("Print project prices:");
        List<ProjectPrices> projectPrices = new DatabaseQueryService().printProjectPrices(database);
        for (ProjectPrices projectPrice : projectPrices) {
            System.out.println(projectPrice);
        }

        database.closeConnection();
    }
}