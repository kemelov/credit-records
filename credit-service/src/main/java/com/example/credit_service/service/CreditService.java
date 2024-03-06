package com.example.credit_service.service;

import com.example.credit_service.entity.Credit;
import com.example.credit_service.repository.CreditRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


public class CreditService {
    public final CreditRepository repository;
    private final static Random RANDOM = new Random();
    private final List<Credit> credits = new ArrayList<>();

    public CreditService(CreditRepository repository) {
        this.repository = repository;
    }

    public long createCredits(int count) {
        // Generate shuffled customer IDs with Stream API (more efficient)
        List<Long> customerIds = generateRandomCustomerId(count);
        long startTime = System.currentTimeMillis();
        final String[] statuses = { "CREATED", "IN PROGRESS", "ACTIVE", "CLOSED"};
        for (int i = 0; i < count; i++) {
            Credit credit = new Credit();
            credit.setCreditCreatedDate(LocalDateTime.now());
            credit.setCreditStatus(statuses[RANDOM.nextInt(4)]);
            credit.setCreditAmount(BigDecimal.valueOf(Math.random() * 10000));
            credit.setCreditOpenDate(LocalDate.now());
            credit.setCreditCloseDate(LocalDate.now().plusDays((int) (Math.random() * 365)));
            credit.setCustomerId(customerIds.get(i)+1);
            credits.add(credit);
        }
        repository.saveAll(credits);
        return System.currentTimeMillis() - startTime;
    }
    private List<Long> generateRandomCustomerId(int count) {
        long p1 = (long) (count * 0.1);
        long p2 = (long) (count * 0.2);
        long p3 = (long) (count * 0.3);
        long p4 = (long) (count * 0.4);

        List<Long> allIds = new ArrayList<>();
        Set<Long> generated = new HashSet<>();

        for (long i = 0; i < p4; i++) {
            allIds.add(i);
        }

        while (generated.size() < p1) {
            generated.add(RANDOM.nextLong(p4));
        }
        allIds.addAll(generated);

        generated.clear();
        while (generated.size() < p2) {
            generated.add(RANDOM.nextLong(p4));
        }
        allIds.addAll(generated);

        generated.clear();
        while (generated.size() < p3) {
            generated.add(RANDOM.nextLong(p4));
        }
        allIds.addAll(generated);

        Collections.shuffle(allIds);
        return allIds;
    }

    public List<Credit> findAll() {
        return repository.findAll();
    }
}