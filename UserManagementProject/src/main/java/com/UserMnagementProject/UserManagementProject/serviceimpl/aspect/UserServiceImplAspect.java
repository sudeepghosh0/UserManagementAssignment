package com.UserMnagementProject.UserManagementProject.serviceimpl.aspect;

import com.UserMnagementProject.UserManagementProject.customexception.DuplicateUserException;
import com.UserMnagementProject.UserManagementProject.customexception.ValidationFailureException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Aspect
@Component
public class UserServiceImplAspect {

    private static final Logger logger = Logger.getLogger(UserServiceImplAspect.class.getName());

    @Before("execution(* com.example.demo.service.impl.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Entering method: " + joinPoint.getSignature().toShortString() + " with arguments: " + joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "execution(* com.example.demo.service.impl.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Exiting method: " + joinPoint.getSignature().toShortString() + " with result: " + result);
    }

    @AfterThrowing(pointcut = "execution(* com.example.demo.service.impl.*.*(..))", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        logger.log(Level.SEVERE, "Exception in method: " + joinPoint.getSignature().toShortString() + " with message: " + error.getMessage(), error);
    }

    @Around("execution(* com.example.demo.service.impl.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (DuplicateUserException | ValidationFailureException ex) {
            logger.log(Level.WARNING, "Method: " + joinPoint.getSignature().toShortString() + " failed with exception: " + ex.getMessage(), ex);
            throw ex;
        } catch (Throwable throwable) {
            logger.log(Level.SEVERE, "Method: " + joinPoint.getSignature().toShortString() + " failed with exception: " + throwable.getMessage(), throwable);
            throw throwable;
        }
    }




}
