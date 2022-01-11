package ru.lis154.stockotus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling

public class StockOtusApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockOtusApplication.class, args);
	}

}
