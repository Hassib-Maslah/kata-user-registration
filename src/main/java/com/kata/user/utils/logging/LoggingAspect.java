package com.kata.user.utils.logging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kata.user.bootstrap.UserBootstrap;
import com.kata.user.utils.JsonUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.IOException;

@Aspect
@Component
public class LoggingAspect {
    Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    private long start;

    @Before("execution(* com.kata.user.web.controller.*.*(..))")
    public void beforeExecution() {
       log.info("==============Start Logging==============");
       start = System.currentTimeMillis();
    }

    @AfterReturning(value = "execution(* com.kata.user.web.controller.*.*(..))", returning = "returnValue")
    public void afterReturning(JoinPoint joinPoint, Object returnValue) throws JsonProcessingException {

        log.info("Inputs: ");
        log.info(!ObjectUtils.isEmpty(joinPoint.getArgs()) ? JsonUtils.toJsonString(joinPoint.getArgs()[0]) : null);
        log.info("Outputs: ");
        log.info(JsonUtils.toJsonString(((ResponseEntity<?>) returnValue).getBody()));

    }

    @AfterThrowing(value = "execution(* com.kata.user.web.controller.*.*(..))", throwing = "e")
    public void afterThrowingAccessDeniedException(JoinPoint joinPoint, Exception e) throws JsonProcessingException {
        log.info("Inputs: ");
        log.info(!ObjectUtils.isEmpty(joinPoint.getArgs()) ? JsonUtils.toJsonString(joinPoint.getArgs()[0]) : null);
        log.info("Outputs: ");
        log.info(e.getMessage());
    }

    @After("execution(* com.kata.user.web.controller.*.*(..))")
    public void afterCompletion(JoinPoint joinPoint) {
        long executionTime = System.currentTimeMillis() - start;

        log.info("Method: " + joinPoint.getSignature() + " executed in " + executionTime + "ms");

        log.info("==============End Logging==============");
    }

}
