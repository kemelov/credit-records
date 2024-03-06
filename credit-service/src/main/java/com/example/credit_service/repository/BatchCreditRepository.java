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
        jdbcTemplate.batchUpdate("INSERT INTO credit " +
                        "(credit_created_date, credit_status, credit_amount, credit_open_date, credit_close_date, customer_id) " +
                        "VALUES (?, ?, ?, ?, ?, ?)",
                credits,
                1000,
                (PreparedStatement ps, Credit credit) -> {
                    ps.setTimestamp(1, Timestamp.valueOf(credit.getCreditCreatedDate()));
                    ps.setString(2, credit.getCreditStatus());
                    ps.setBigDecimal(3, credit.getCreditAmount());
                    ps.setDate(4, Date.valueOf(credit.getCreditOpenDate()));
                    ps.setDate(5, Date.valueOf(credit.getCreditCloseDate()));
                    ps.setLong(6, credit.getCustomerId());
                }
        );
    }

    @Override
    public List<Credit> findAll() {
        String sql = "SELECT * FROM credit";

        return jdbcTemplate.query(sql, (rs, rowNum) -> { // Row mapper
            Credit credit = new Credit();
            credit.setId(rs.getLong("id"));
            credit.setCreditCreatedDate(rs.getTimestamp("credit_created_date").toLocalDateTime());
            credit.setCreditStatus(rs.getString("credit_status"));
            credit.setCreditAmount(rs.getBigDecimal("credit_amount"));
            credit.setCreditOpenDate(rs.getDate("credit_open_date").toLocalDate());
            credit.setCreditCloseDate(rs.getDate("credit_close_date").toLocalDate());
            credit.setCustomerId(rs.getLong("customer_id"));
            return credit;
        });
    }
}
