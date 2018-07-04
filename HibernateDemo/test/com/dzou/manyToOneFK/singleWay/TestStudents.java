package com.dzou.manyToOneFK.singleWay;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.junit.Test;

import java.util.Date;
import java.util.EnumSet;

public class TestStudents {

    @Test
    public void testSchemaExport() {

        /**
         * 生成表结构
         */
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
        Metadata metadata = new MetadataSources(serviceRegistry).buildMetadata();
        SchemaExport schemaExport = new SchemaExport();
        schemaExport.create(EnumSet.of(TargetType.DATABASE), metadata);

    }


    @Test
    public void addStudents() {

        // 创建服务注册对象
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
        // 创建会话工厂对象
        SessionFactory sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
        // 创建会话对象
        Session session = sessionFactory.getCurrentSession();
        // 开启事务
        Transaction transaction = session.beginTransaction();


        ClassRoom c1 = new ClassRoom("C001", "软件工程");
        ClassRoom c2 = new ClassRoom("C002", "网络工程");

        Students s1 = new Students("张三","男", new Date(), "计算机", c1);
        Students s2 = new Students("张四","女", new Date(), "计算机", c1);
        Students s3 = new Students("张五","男", new Date(), "计算机", c2);
        Students s4 = new Students("张六","女", new Date(), "计算机", c2);

        session.save(c1);
        session.save(c2);
        session.save(s1);
        session.save(s2);
        session.save(s3);
        session.save(s4);

        // 提交事务
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

}
