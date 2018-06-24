package com.dzou;

import com.dzou.dao.userMapper;
import com.dzou.pojo.user;
import com.dzou.utils.GetSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * 示例: 通过接口操作数据库
 */

public class test1 {

    private static SqlSession sqlSession;
    private static userMapper mapper;


    @BeforeClass
    public static void setUp() {

        SqlSessionFactory sqlSessionFactory = GetSqlSessionFactory.getInstance();
        sqlSession = sqlSessionFactory.openSession();
        mapper = sqlSession.getMapper(userMapper.class);
    }

    @AfterClass
    public static void tearDown() {

        sqlSession.clearCache();
        sqlSession.close();
    }


    @Test
    public void testAddUser() {

        user users = new user();
        users.setName("dzou");

        int result = mapper.insert(users);
        sqlSession.commit();

        System.out.println("result = " + result);
    }

    @Test
    public void testQueryUser() {

        user users = mapper.selectByPrimaryKey(6);
        System.out.println(users);
    }

}
