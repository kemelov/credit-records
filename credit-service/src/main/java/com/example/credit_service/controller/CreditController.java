package com.example.credit_service.controller;

import com.example.credit_service.entity.Credit;
import com.example.credit_service.service.CreditService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/credits")
public class CreditController {
    private final CreditService service;

    public CreditController(CreditService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Credit>> getAllCredits() {
        List<Credit> credits = service.findAll();
        if (credits.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(credits, HttpStatus.OK);
    }
}
