package com.kcm.demo.repository;

import org.springframework.jdbc.core.JdbcTemplate;

//DB 관리
// CRUD
public class EventRepository {

    private final JdbcTemplate jdbctemplate;

    public EventRepository(JdbcTemplate jdbctemplate) {
        this.jdbctemplate = jdbctemplate;
    }
}
