package com.dzou.oneToOneFK.singleWay;


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

        IdCard card = new IdCard("420281190001010836", "张无忌");
        Students student = new Students("男", new Date(), "太极拳", card);

        session.save(card);
        session.save(student);

        // 提交事务
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

}
