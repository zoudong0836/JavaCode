package com.dzou.spring.jdbcTemplates;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JTest {

    private ApplicationContext ctx;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    {
        ctx = new ClassPathXmlApplicationContext("com/dzou/spring/jdbcTemplates/spring-config-jdbc.xml");
        jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) ctx.getBean("namedParameterJdbcTemplate");
    }


    /**
     * 测试数据库连接
     *
     * @throws SQLException
     */
    @Test
    public void testDataSource() throws SQLException {

        DataSource dataSource = ctx.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());
    }

    /**
     * 添加一条记录
     */
    @Test
    public void testUpdate() {

        String sql = "update users set age = ? where id = ?";
        jdbcTemplate.update(sql, 22, 1);
    }

    /**
     * 执行批量操作
     */
    @Test
    public void testBatchUpdate() {

        String sql = "INSERT INTO users(name, age) VALUES(?, ?)";

        List<Object[]> batchArgs = new ArrayList<>();
        batchArgs.add(new Object[]{"AA", 30});
        batchArgs.add(new Object[]{"BB", 31});
        batchArgs.add(new Object[]{"CC", 32});

        jdbcTemplate.batchUpdate(sql, batchArgs);
    }

    /**
     * 从数据库中获取一条记录, 返回一个对象
     * 1. RowMapper指定如何去映射结果集的行, 常用的实现类为 BeanPropertyRowMappe
     * 2. 使用 SQL 中列的别名完成列名和类的属性名的映射. 例如last_name lastName
     * 3. 不支持级联属性. JdbcTemplate 到底是一个 JDBC 的小工具, 而不是 ORM 框架
     */
    @Test
    public void testQueryForObject() {

        String sql = "SELECT id, name, age FROM users where id = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        User user = jdbcTemplate.queryForObject(sql, rowMapper, 1);
        System.out.println(user);
    }

    /**
     * 返回一个对象集合
     */
    @Test
    public void testQueryForObjectList() {

        String sql = "SELECT id, name, age FROM users where id > ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        List<User> users = jdbcTemplate.query(sql, rowMapper, 1);
        System.out.println(users);
    }

    /**
     * 查询单个值
     */
    @Test
    public void testQueryForSingleValue() {

        String sql = "SELECT count(*) FROM users";
        long count = jdbcTemplate.queryForObject(sql, Long.class);
        System.out.println(count);
    }

    /**
     * 为参数命名
     */
    @Test
    public void testNamedParameterJdbcTemplate() {

        String sql = "INSERT INTO users(name, age) VALUES(:name, :age)";

        Map<String, Object> paramSource = new HashMap<>();
        paramSource.put("name", "FF");
        paramSource.put("age", 40);

        namedParameterJdbcTemplate.update(sql, paramSource);
    }

    /**
     * 通过实例对象更新SQL语句
     * 如果SQL语句中的参数名和类的属性不一致时, 需要使用SQL中列的别名完成列名和类的属性名的映射
     */
    @Test
    public void testNamedParameterJdbcTemplateObject() {

        String sql = "INSERT INTO users(name, age) VALUES(:name, :age)";

        User user = new User(-1, "GG", 41);
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);

        namedParameterJdbcTemplate.update(sql, parameterSource);
    }
}
