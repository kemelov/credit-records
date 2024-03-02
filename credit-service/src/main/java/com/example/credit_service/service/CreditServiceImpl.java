package com.example.credit_service.service;

import com.example.credit_service.entity.Credit;
import com.example.credit_service.repository.CreditRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static com.example.credit_service.entity.CreditStatus.getRandomStatus;

@Service
public class CreditServiceImpl implements CreditService, CommandLineRunner {
    public final CreditRepository creditRepository;
    private static final Random RANDOM = new Random();
    private static final int BATCH_SIZE = 500;
    private static final int TOTAL_RECORDS = 2_000_000;

    public CreditServiceImpl(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    @PostConstruct
    public void populateTable() {
        if (creditRepository.count() < TOTAL_RECORDS) {
            List<Credit> credits = new ArrayList<>();
            for (int i = 1; i <= TOTAL_RECORDS; i++) {
                Credit credit = new Credit();
                credit.setCreatedDate(LocalDateTime.now());
                credit.setStatus(getRandomStatus());
                credit.setAmount(Math.random() * 10000);
                credit.setOpenDate(LocalDate.now());
                credit.setCloseDate(LocalDate.now().plusDays((int) (Math.random() * 365)));
                credit.setCustomerId(getRandomCustomerId());
                credits.add(credit);

                if (i % BATCH_SIZE == 0) {
                    creditRepository.saveAll(credits);
                    credits.clear();
                }
            }

            if (!credits.isEmpty()) {
                creditRepository.saveAll(credits);
            }
        }
    }

    @Override
    public void run(String... args) {
        populateTable();
    }

    private Long getRandomCustomerId() {
        int customerIdDistribution = RANDOM.nextInt(100);
        if (customerIdDistribution < 10) {
            return RANDOM.nextLong(CreditServiceImpl.TOTAL_RECORDS / 10) + 1;
        } else if (customerIdDistribution < 30) {
            return RANDOM.nextLong(CreditServiceImpl.TOTAL_RECORDS / 5) + 1;
        } else if (customerIdDistribution < 60) {
            return RANDOM.nextLong(CreditServiceImpl.TOTAL_RECORDS / 3) + 1;
        } else {
            return RANDOM.nextLong(CreditServiceImpl.TOTAL_RECORDS / 2) + 1;
        }
    }
}