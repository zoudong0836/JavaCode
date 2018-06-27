package com.dzou.spring.jdbcTemplates;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.SQLException;

public class JTest {

    private ApplicationContext ctx;
    private JdbcTemplate jdbcTemplate;

    {
        ctx = new ClassPathXmlApplicationContext("com/dzou/spring/jdbcTemplates/spring-config-jdbc.xml");
        jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
    }


    @Test
    public void testDataSource() throws SQLException {

        DataSource dataSource = ctx.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void testUpdate() {

        String sql = "update users set age = ? where id = ?";
        jdbcTemplate.update(sql, 22, 1);
    }

    @Test
    public void testQueryForObject() {

        String sql = "SELECT id, name, age FROM users where id = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        User user = jdbcTemplate.queryForObject(sql, rowMapper, 1);
        System.out.println(user);
    }
}
