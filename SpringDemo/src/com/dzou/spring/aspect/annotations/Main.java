package com.dzou.spring.aspect.annotations;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("com/dzou/spring/aspect/annotations/annotations/spring-config-aop-xml.xml");
        ArithmeticCalculator arithmeticCalculator = (ArithmeticCalculator) ctx.getBean("arithmeticCalculator");

        int result = arithmeticCalculator.add(11, 12);
        System.out.println("result: " + result);

        //result = arithmeticCalculator.div(21, 0);
        //System.out.println("result: " + result);
    }
}
