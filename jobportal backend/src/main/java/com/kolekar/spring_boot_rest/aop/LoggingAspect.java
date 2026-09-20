package com.kolekar.spring_boot_rest.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.kolekar.spring_boot_rest.service.JobService.getJob(..)) || execution(* com.kolekar.spring_boot_rest.service.JobService.getAllJobs(..))")
    public void logMethodCall(JoinPoint jp){
        LOGGER.info("Method Called " + jp.getSignature().getName());
    }

    @After("execution(* com.kolekar.spring_boot_rest.service.JobService.getJob(..)) || execution(* com.kolekar.spring_boot_rest.service.JobService.getAllJobs(..))")
    public void logMethodExecuted(JoinPoint jp){
        LOGGER.info("Method Executed " + jp.getSignature().getName());
    }

    @AfterThrowing("execution(* com.kolekar.spring_boot_rest.service.JobService.getJob(..)) || execution(* com.kolekar.spring_boot_rest.service.JobService.getAllJobs(..))")
    public void logMethodCrash(JoinPoint jp){
        LOGGER.info("Method has some issue " + jp.getSignature().getName());
    }


    @AfterReturning("execution(* com.kolekar.spring_boot_rest.service.JobService.getJob(..)) || execution(* com.kolekar.spring_boot_rest.service.JobService.getAllJobs(..))")
    public void logMethodExecutedSuccess(JoinPoint jp){
        LOGGER.info("Method Executed sucessfully " + jp.getSignature().getName());
    }
}
