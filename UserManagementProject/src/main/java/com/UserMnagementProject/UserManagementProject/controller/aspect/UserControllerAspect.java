package com.UserMnagementProject.UserManagementProject.controller.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import java.util.logging.Level;
import java.util.logging.Logger;

@Aspect
@Component
public class UserControllerAspect {

    private static final Logger logger = Logger.getLogger(UserControllerAspect.class.getName());

    @Before("execution(* com.example.demo.controller.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Entering method: " + joinPoint.getSignature().toShortString() + " with arguments: " + joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "execution(* com.example.demo.controller.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Exiting method: " + joinPoint.getSignature().toShortString() + " with result: " + result);
    }

    @AfterThrowing(pointcut = "execution(* com.example.demo.controller.*.*(..))", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        logger.log(Level.SEVERE, "Exception in method: " + joinPoint.getSignature().toShortString() + " with message: " + error.getMessage(), error);
    }

    @Around("execution(* com.example.demo.controller.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long endTime = System.currentTimeMillis();
            logger.info("Method: " + joinPoint.getSignature().toShortString() + " executed in " + (endTime - startTime) + "ms");
            return result;
        } catch (Throwable throwable) {
            long endTime = System.currentTimeMillis();
            logger.log(Level.SEVERE, "Method: " + joinPoint.getSignature().toShortString() + " failed in " + (endTime - startTime) + "ms with exception: " + throwable.getMessage(), throwable);
            throw throwable;
        }
    }
}
