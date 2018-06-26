package com.dzou.spring.proxy;

public class Main {

    public static void main(String[] args) {

        // 创建实例对象
        ArithmeticCalculator arithmeticCalculator = new ArithmeticCalculatorImpl();

        // 使用一个代理将对象包装起来,
        // 然后用该代理对象取代原始对象. 任何对原始对象的
        // 调用都要通过代理. 代理对象决定是否以及何时将方法调用转到原始对象上
        arithmeticCalculator = new ArithmeticCalculatorProxy(arithmeticCalculator).getLoggingProxy();

        int result = arithmeticCalculator.add(11, 22);
        System.out.println("result: " + result);

        result = arithmeticCalculator.div(44, 22);
        System.out.println("result: " + result);
    }
}
