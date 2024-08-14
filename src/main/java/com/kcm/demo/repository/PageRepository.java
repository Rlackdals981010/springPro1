package com.kcm.demo.repository;

import com.kcm.demo.entity.Event;
import com.kcm.demo.entity.Page;
import com.kcm.demo.entity.PageSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PageRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired//생성자 1개일떄 생략 가능
    public PageRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Page> findByPageNum(Long pageNum) {
        PageSpec pageSpec = new PageSpec(pageNum);

        Long offset = pageSpec.getStartNum();
        Long pageSize = pageSpec.getPageSize();

        String sql = "SELECT e.eventId, e.todo, e.manId, e.createDay, e.updateDay, m.name " +
                "from event e join manager m on e.manId = m.manId " +
                "ORDER BY e.eventId LIMIT ?, ?";

        return jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            Page page = new Page();
            page.setEventId(resultSet.getLong("e.eventId"));
            page.setTodo(resultSet.getString("e.todo"));
            page.setManId(resultSet.getString("e.manId"));
            page.setCreateDay(resultSet.getDate("e.createDay"));
            page.setUpdateDay(resultSet.getDate("e.updateDay"));
            page.setName(resultSet.getString("m.name"));
            return page;
        }, offset, pageSize);

    }
}
