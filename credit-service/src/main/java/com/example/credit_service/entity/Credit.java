package com.example.credit_service.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime creditCreatedDate;

    private String creditStatus;

    private BigDecimal creditAmount;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDate creditOpenDate;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDate creditCloseDate;

    private Long customerId;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedDate() {
        return creditCreatedDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.creditCreatedDate = createdDate;
    }

    public String getStatus() {
        return creditStatus;
    }

    public void setStatus(String status) {
        this.creditStatus = status;
    }

    public BigDecimal getAmount() {
        return creditAmount;
    }

    public void setAmount(BigDecimal amount) {
        this.creditAmount = amount;
    }

    public LocalDate getOpenDate() {
        return creditOpenDate;
    }

    public void setOpenDate(LocalDate openDate) {
        this.creditOpenDate = openDate;
    }

    public LocalDate getCloseDate() {
        return creditCloseDate;
    }

    public void setCloseDate(LocalDate closeDate) {
        this.creditCloseDate = closeDate;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
