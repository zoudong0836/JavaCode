package com.dzou;

import com.dzou.dao.UserMapper;
import com.dzou.pojo.User;
import com.dzou.utils.GetApplicationContext;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;


/**
 * 示例: 通过接口操作数据库
 */

public class test1 {

    private static UserMapper mapper;


    @BeforeClass
    public static void setUp() {

        ApplicationContext applicationContext = GetApplicationContext.getInstance();
        mapper = (UserMapper) applicationContext.getBean("userMaper");
    }


    @Test
    public void testAddUser() {

        User user = new User();
        user.setName("dzou");

        mapper.addUser(user);
    }

    @Test
    public void testQueryUser() {

        User user = mapper.selectById(8);
        System.out.println(user);
    }

}
