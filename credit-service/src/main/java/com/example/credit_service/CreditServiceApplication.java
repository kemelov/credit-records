package com.example.credit_service;

import com.example.credit_service.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CreditServiceApplication implements CommandLineRunner {

	@Autowired
	private CreditService creditService;

	public static void main(String[] args) {
		SpringApplication.run(CreditServiceApplication.class, args);
	}
	@Override
	public void run(String... args) {
		long duration = creditService.createCredits(2_000_000);
		System.out.println("Время сздания всех записей: " + duration + " миллисекунд");
	}

}
