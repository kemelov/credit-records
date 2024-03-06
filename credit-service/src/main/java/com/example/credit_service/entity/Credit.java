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

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate creditOpenDate;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate creditCloseDate;

    private Long customerId;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreditCreatedDate() {
        return creditCreatedDate;
    }

    public void setCreditCreatedDate(LocalDateTime createdDate) {
        this.creditCreatedDate = createdDate;
    }

    public String getCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(String creditStatus) {
        this.creditStatus = creditStatus;
    }

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    public LocalDate getCreditOpenDate() {
        return creditOpenDate;
    }

    public void setCreditOpenDate(LocalDate creditOpenDate) {
        this.creditOpenDate = creditOpenDate;
    }

    public LocalDate getCreditCloseDate() {
        return creditCloseDate;
    }

    public void setCreditCloseDate(LocalDate creditCloseDate) {
        this.creditCloseDate = creditCloseDate;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
