package com.dzou.spring.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ArithmeticCalculatorProxy {

    private ArithmeticCalculator target;

    public ArithmeticCalculatorProxy(ArithmeticCalculator target) {
        super();
        this.target = target;
    }

    public ArithmeticCalculator getLoggingProxy() {

        ArithmeticCalculator proxy;

        ClassLoader loader = target.getClass().getClassLoader();
        Class[] interfaces = new Class[]{ArithmeticCalculator.class};

        InvocationHandler h = new InvocationHandler() {

            /**
             * proxy: 代理对象。 一般不使用该对象(容易导致循环调用)
             * method: 正在被调用的方法
             * args: 调用方法传入的参数
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                String methodName = method.getName();

                //打印前置日志
                System.out.println("[Before] The method " + methodName + " begins with " + Arrays.asList(args));

                Object result = null;
                try {
                    result = method.invoke(target, args);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

                //打印后置日志 (因为方法可以能会出异常, 所以访问不到方法的返回值)
                System.out.println("[After] The method ends with " + result);
                return result;
            }
        };

        /**
         * loader: 代理对象使用的类加载器。
         * interfaces: 指定代理对象的类型. 即代理代理对象中可以有哪些方法.
         * h: 当具体调用代理对象的方法时, 应该如何进行响应, 实际上就是调用 InvocationHandler 的 invoke 方法
         */
        proxy = (ArithmeticCalculator) Proxy.newProxyInstance(loader, interfaces, h);
        return proxy;
    }
}
