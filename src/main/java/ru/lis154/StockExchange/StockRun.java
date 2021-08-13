package ru.lis154.StockExchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StockRun implements CommandLineRunner {


    @Autowired
    private TestCompany testCompany;

    public static void main(String[] args) {
        SpringApplication.run(StockRun.class, args);
    }

    @Override
    public void run(String... strings) {
        testCompany.testCompany();
    }
}
