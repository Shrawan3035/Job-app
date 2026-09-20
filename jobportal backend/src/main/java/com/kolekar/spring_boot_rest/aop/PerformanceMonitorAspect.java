package com.kolekar.spring_boot_rest.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerformanceMonitorAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceMonitorAspect.class);

    @Around("execution(* com.kolekar.spring_boot_rest.service.JobService.getJob(..))")
    public  Object  monitorTime(ProceedingJoinPoint jp) throws Throwable {

        long startTime = System.currentTimeMillis();

        Object obj = jp.proceed();

        long endTime = System.currentTimeMillis();

        LOGGER.info("Time taken by " + jp.getSignature().getName() + " method : " + (endTime - startTime) + " ms");

        return obj;
    }
}
