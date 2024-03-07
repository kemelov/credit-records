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
                        "(created_date, status, amount, open_date, close_date, customer_id) " +
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

    @Override
    public List<Credit> findAll() {
        String sql = "SELECT * FROM credit";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Credit credit = new Credit();
            credit.setId(rs.getLong("id"));
            credit.setCreatedDate(rs.getTimestamp("created_date").toLocalDateTime());
            credit.setStatus(rs.getString("status"));
            credit.setAmount(rs.getDouble("amount"));
            credit.setOpenDate(rs.getDate("open_date").toLocalDate());
            credit.setCloseDate(rs.getDate("close_date").toLocalDate());
            credit.setCustomerId(rs.getLong("customer_id"));
            return credit;
        });
    }
}
