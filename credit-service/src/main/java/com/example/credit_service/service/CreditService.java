package com.example.credit_service.service;

import com.example.credit_service.entity.Credit;
import com.example.credit_service.repository.CreditRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreditService {
    public final CreditRepository creditRepository;
    private final static  int TOTAL_RECORDS = 2_000_000;
    private final static Random RANDOM = new Random();

    public CreditService(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    public long createCredits(int count) {
        long startTime = System.currentTimeMillis();
        final List<Credit> credits = new ArrayList<>();
        final String[] statuses = { "CREATED", "IN PROGRESS", "ACTIVE", "CLOSED"};
        for (int i = 1; i <= count; i++) {
            Credit credit = new Credit();
            credit.setCreatedDate(LocalDateTime.now());
            credit.setStatus(statuses[RANDOM.nextInt(4)]);
            credit.setAmount(Math.random() * 10000);
            credit.setOpenDate(LocalDate.now());
            credit.setCloseDate(LocalDate.now().plusDays((int) (Math.random() * 365)));
            credit.setCustomerId(getRandomCustomerId());
            credits.add(credit);
        }
        creditRepository.saveAll(credits);
        return System.currentTimeMillis() - startTime;
    }
    private Long getRandomCustomerId() {
        int customerIdDistribution = RANDOM.nextInt(100);
        if (customerIdDistribution < 10) {
            return RANDOM.nextLong(CreditService.TOTAL_RECORDS / 10);
        } else if (customerIdDistribution < 30) {
            return RANDOM.nextLong(CreditService.TOTAL_RECORDS / 5);
        } else if (customerIdDistribution < 60) {
            return RANDOM.nextLong(CreditService.TOTAL_RECORDS / 3);
        } else {
            return RANDOM.nextLong(CreditService.TOTAL_RECORDS / 2);
        }
    }

}