package com.lllockkk.security.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by L on 2017/8/21.
 */
@Aspect
@Component
public class TimeAspect {

    @Pointcut("execution(* com.lllockkk.security.web.controller.UserController.*(..))")
    public void anyMethod() {}


    @Around("anyMethod()")
    public Object handleControllerMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("time aspect start");
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        System.out.println("time aspect: " + (end - start));
        System.out.println("time aspect end");
        return result;
    }

}
