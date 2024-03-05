package com.example.credit_service.service;

import com.example.credit_service.entity.Credit;
import com.example.credit_service.repository.CreditRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class CreditService {
    public final CreditRepository creditRepository;
    public final static  int TOTAL_RECORDS = 2_000_000;
    private final static Random RANDOM = new Random();
    private final List<Credit> credits = new ArrayList<>();

    public CreditService(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    public long createCredits(int count) {
        // Generate shuffled customer IDs with Stream API (more efficient)
        List<Long> customerIds = generateRandomCustomerId();
        long startTime = System.currentTimeMillis();
        final String[] statuses = { "CREATED", "IN PROGRESS", "ACTIVE", "CLOSED"};
        for (int i = 0; i < count; i++) {
            Credit credit = new Credit();
            credit.setCreatedDate(LocalDateTime.now());
            credit.setStatus(statuses[RANDOM.nextInt(4)]);
            credit.setAmount(BigDecimal.valueOf(Math.random() * 10000));
            credit.setOpenDate(LocalDate.now());
            credit.setCloseDate(LocalDate.now().plusDays((int) (Math.random() * 365)));
            credit.setCustomerId(customerIds.get(i)+1);
            credits.add(credit);
        }
        creditRepository.saveAll(credits);
        return System.currentTimeMillis() - startTime;
    }
    private List<Long> generateRandomCustomerId() {
        long p1 = (long) (TOTAL_RECORDS * 0.1);
        long p2 = (long) (TOTAL_RECORDS * 0.2);
        long p3 = (long) (TOTAL_RECORDS * 0.3);
        long p4 = (long) (TOTAL_RECORDS * 0.4);

        List<Long> uniqueIds = new ArrayList<>();
        List<Long> allIds = new ArrayList<>();
        Set<Long> generated1 = new HashSet<>();

        for (long i = 0; i < p4; i++) {
            uniqueIds.add(i);
        }

        while (generated1.size() < p1) {
            generated1.add(RANDOM.nextLong(uniqueIds.size()));
        }
        Set<Long> generated2 = new HashSet<>(generated1);

        while (generated2.size() < p2) {
            generated2.add(RANDOM.nextLong(uniqueIds.size()));
        }
        Set<Long> generated3 = new HashSet<>(generated2);

        while (generated3.size() < p3) {
            generated3.add(RANDOM.nextLong(uniqueIds.size()));
        }

        generated3.removeAll(generated2);
        generated2.removeAll(generated1);
        allIds.addAll(uniqueIds);
        allIds.addAll(generated1);
        allIds.addAll(generated2);
        allIds.addAll(generated2);
        allIds.addAll(generated3);
        allIds.addAll(generated3);
        allIds.addAll(generated3);
        Collections.shuffle(allIds);

        return allIds;
    }


}