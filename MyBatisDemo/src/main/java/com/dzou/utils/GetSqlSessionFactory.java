package com.dzou.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class GetSqlSessionFactory {

    private static SqlSessionFactory sqlSessionFactory;

    private GetSqlSessionFactory() {

    }

    public static SqlSessionFactory getInstance() {

        if(sqlSessionFactory == null) {

            synchronized (GetSqlSessionFactory.class) {

                if(sqlSessionFactory == null) {

                    try {

                        // 配置log4j
                        //InputStream is = Resources.getResourceAsStream("log4j.properties");
                        //PropertyConfigurator.configure(is);

                        // 配置mybatis
                        Reader reader = Resources.getResourceAsReader("mybatis/mybatis-config.xml");
                        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return sqlSessionFactory;
    }
}
