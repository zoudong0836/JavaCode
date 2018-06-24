package com.dzou.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GetApplicationContext {

    private static ApplicationContext applicationContext;

    private GetApplicationContext() {

    }

    public static ApplicationContext getInstance() {

        if(applicationContext == null) {

            synchronized (GetApplicationContext.class) {

                if(applicationContext == null) {

                    applicationContext = new ClassPathXmlApplicationContext("spring/spring-config.xml");
                }
            }
        }

        return applicationContext;
    }
}
