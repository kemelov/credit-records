package com.example.credit_service.model;

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
}
