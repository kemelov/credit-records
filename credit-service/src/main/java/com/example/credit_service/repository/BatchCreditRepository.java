package com.example.credit_service.repository;

import com.example.credit_service.entity.Credit;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class BatchCreditRepository implements CreditRepository {

    private final JdbcTemplate jdbcTemplate;

    public BatchCreditRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void saveAll(List<Credit> credits) {
        jdbcTemplate.batchUpdate("INSERT INTO tb_credit " +
                        "(credit_created_date, credit_status, credit_amount, credit_open_date, credit_close_date, customer_id) " +
                        "VALUES (?, ?, ?, ?, ?, ?)",
                credits,
                1000,
                (PreparedStatement ps, Credit credit) -> {
                    ps.setTimestamp(1, Timestamp.valueOf(credit.getCreatedDate()));
                    ps.setString(2, credit.getStatus());
                    ps.setDouble(3, credit.getAmount());
                    ps.setDate(4, Date.valueOf(credit.getOpenDate()));
                    ps.setDate(5, Date.valueOf(credit.getCloseDate()));
                    ps.setLong(6, credit.getCustomerId());
                }
        );
    }
}
