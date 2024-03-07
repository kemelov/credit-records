package com.example.credit_service.repository;


import com.example.credit_service.entity.Credit;

import java.util.List;

public interface CreditRepository {
    void saveAll(List<Credit> credits);
    List<Credit> findAll();
}