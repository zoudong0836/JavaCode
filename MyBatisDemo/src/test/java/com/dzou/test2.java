package com.dzou;


import com.dzou.pojo.user;
import com.dzou.utils.GetSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 示例: 通过配置文件(userMapper.xml)操作数据库
 */

public class test2 {

    private static SqlSession sqlSession;

    @BeforeClass
    public static void setUp() {

        SqlSessionFactory sqlSessionFactory = GetSqlSessionFactory.getInstance();
        sqlSession = sqlSessionFactory.openSession();
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

        int result = sqlSession.insert("com.dzou.dao.userMapper" + ".insert", users);
        sqlSession.commit();

        System.out.println("result = " + result);
    }

    @Test
    public void testSelectUser() {

        user users = sqlSession.selectOne("com.dzou.dao.userMapper" + ".selectByPrimaryKey", 6);
        System.out.println(users);
    }

    @Test
    public void testUpdateUser() {

        user users = new user();
        users.setId(7);
        users.setName("zheng");

        int result = sqlSession.update("com.dzou.dao.userMapper" + ".updateByPrimaryKey", users);
        sqlSession.commit();

        System.out.println(result);
    }
}
