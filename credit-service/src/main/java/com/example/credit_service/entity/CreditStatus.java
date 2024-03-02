package com.example.credit_service.entity;

import java.util.Random;

public enum CreditStatus {
    CREATED ("СОЗДАН"),
    IN_PROCESS ("В ПРОЦЕССЕ"),
    ACTIVE ("АКТИВНЫЙ"),
    CLOSED ("ЗАКРЫТ");

    private final String value;

    CreditStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private static final Random RANDOM = new Random();

    public static CreditStatus getRandomStatus() {
        CreditStatus[] statuses = values();
        return statuses[RANDOM.nextInt(statuses.length)];
    }
}
