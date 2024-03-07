package com.example.credit_service.service;

import com.example.credit_service.entity.Credit;
import com.example.credit_service.repository.CreditRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Collections;
import java.util.Set;

@Service
public class CreditService {
    public final CreditRepository repository;
    private final static Random RANDOM = new Random();

    public CreditService(CreditRepository repository) {
        this.repository = repository;
    }

    public long createCredits(int count) {
        List<Credit> credits = new ArrayList<>(count);
        List<Long> customerIds = generateRandomCustomerId(count);
        long startTime = System.currentTimeMillis();
        final String[] statuses = { "CREATED", "IN PROCESS", "ACTIVE", "CLOSED"};
        final Double[] amounts = { 1000.0, 2000.0, 3000.0, 4000.0, 5000.0, 6000.0, 7000.0, 8000.0, 9000.0, 10000.0 };

        for (int i = 0; i < count; i++) {
            Credit credit = new Credit();
            credit.setCreatedDate(LocalDateTime.now());
            credit.setStatus(statuses[RANDOM.nextInt(4)]);
            credit.setAmount(amounts[RANDOM.nextInt(10)]);
            credit.setOpenDate(LocalDate.now());
            credit.setCloseDate(LocalDate.now().plusDays(RANDOM.nextInt(1, 365)));
            credit.setCustomerId(customerIds.get(i)+1);
            credits.add(credit);
        }
        repository.saveAll(credits);
        return System.currentTimeMillis() - startTime;
    }
    private List<Long> generateRandomCustomerId(int count) {
        int percent10 = (int) (count * 0.1);
        int percent20 = (int) (count * 0.2);
        int percent30 = (int) (count * 0.3);
        int percent40 = (int) (count * 0.4);

        List<Long> allIds = new ArrayList<>();
        Set<Long> generated = new HashSet<>();

        for (long i = 0; i < percent40; i++) {
            allIds.add(i);
        }

        while (generated.size() < percent10) {
            generated.add(RANDOM.nextLong(percent40));
        }

        allIds.addAll(generated);
        generated.clear();

        while (generated.size() < percent20) {
            generated.add(RANDOM.nextLong(percent40));
        }

        allIds.addAll(generated);
        generated.clear();

        while (generated.size() < percent30) {
            generated.add(RANDOM.nextLong(percent40));
        }

        allIds.addAll(generated);
        Collections.shuffle(allIds);

        return allIds;
    }

    public List<Credit> listAll() {
        return repository.findAll();
    }
}