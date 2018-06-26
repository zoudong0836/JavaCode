package com.dzou.spring.aspect.annotations;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect         // 声明为一个切面
@Component       // 切面必须是IoC中的Bean
public class ArithmeticCalculatorAspect {


    // 定义一个方法, 用于声明切入点表达式 (一般地, 该方法中不再需要添入其它 的代码)
    @Pointcut("execution(* com.dzou.spring.aspect.annotations.ArithmeticCalculatorImpl.*(int, int))")
    public void declareJoinPointExpression() {
    }


    // 前置通知, 在方法执行之前执行
    @Before("declareJoinPointExpression()")
    public void beforeMethod(JoinPoint joinPoint) {

        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        System.out.println("The method " + methodName + " begins with " + Arrays.asList(args));
    }

    // 后置通知, 在方法执行之后执行 (无论该方法是否出现异常)
    @After("declareJoinPointExpression()")
    public void AfterMethod(JoinPoint joinPoint) {

        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method " + methodName + " ends");
    }

    // 返回通知, 在方法返回结果之后执行 (可以访问方法的返回结果)
    @AfterReturning(value = "declareJoinPointExpression()", returning = "result")
    public void afterRunning(JoinPoint joinPoint, Object result) {

        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method " + methodName + " ends with result [ " + result + " ]");
    }

    // 异常通知, 在方法抛出异常之后
    @AfterThrowing(value = "declareJoinPointExpression()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {

        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method " + methodName + " occurs exception: " + ex);
    }


    /**
     * 环绕通知需携带 ProceedingJoinPoint 类型的参数
     * 环绕通知类似于动态代理的全过程: ProceedingJoinPoint 类型的参数可以决定是否执行目标方法
     * 环绕通知必须由返回值, 返回值即为目标方法的返回值
     */
    @Around("declareJoinPointExpression()")
    public Object around(ProceedingJoinPoint pjd) {

        Object result = null;
        String methodName = pjd.getSignature().getName();

        try {
            // 前置通知
            System.out.println("The method " + methodName + " begins with " + Arrays.asList(pjd.getArgs()));

            // 执行目标方法
            result = pjd.proceed();

            // 返回通知
            System.out.println("The method " + methodName + " ends with " + result);
        } catch (Throwable e) {
            // 异常通知
            System.out.println("The method " + methodName + " occurs exception: " + e);

            e.printStackTrace();
        }

        // 后置通知
        System.out.println("The method " + methodName + " ends");

        return result;
    }
}
