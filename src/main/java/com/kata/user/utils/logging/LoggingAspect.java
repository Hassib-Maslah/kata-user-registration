package com.kata.user.utils.logging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kata.user.utils.JsonUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * This is an Aspect class dedicated to log inputs and outputs as well as the processing time for all method in the classes under web/controller package
 */
@Aspect
@Component
public class LoggingAspect {
    Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    private long start;

    /**
     * This method executed before execution of each method in the classes under web/controller package.
     * It initializes start time of the execution of the method.
     */
    @Before("execution(* com.kata.user.web.controller.*.*(..))")
    public void beforeExecution() {
        log.info("==============Start Logging==============");
        start = System.currentTimeMillis();
    }

    /**
     * This method executed after a successful execution of each method in the classes under web/controller package.
     * It contains logging of the input and output of the method.
     */
    @AfterReturning(value = "execution(* com.kata.user.web.controller.*.*(..))", returning = "returnValue")
    public void afterReturning(JoinPoint joinPoint, Object returnValue) throws JsonProcessingException {

        log.info("Inputs: ");
        log.info(!ObjectUtils.isEmpty(joinPoint.getArgs()) ? JsonUtils.toJsonString(joinPoint.getArgs()[0]) : null);
        log.info("Outputs: ");
        log.info(JsonUtils.toJsonString(((ResponseEntity<?>) returnValue).getBody()));

    }

    /**
     * This method executed after a failed execution of each method in the classes under web/controller package.
     * It contains logging of the input and the error raised by the method.
     */
    @AfterThrowing(value = "execution(* com.kata.user.web.controller.*.*(..))", throwing = "e")
    public void afterThrowingAccessDeniedException(JoinPoint joinPoint, Exception e) throws JsonProcessingException {
        log.info("Inputs: ");
        log.info(!ObjectUtils.isEmpty(joinPoint.getArgs()) ? JsonUtils.toJsonString(joinPoint.getArgs()[0]) : null);
        log.info("Outputs: ");
        log.info(e.getMessage());
    }

    /**
     * This method executed after a successful/failed execution of each method in the classes under web/controller package.
     * It contains logging of the method signature and its execution time.
     */
    @After("execution(* com.kata.user.web.controller.*.*(..))")
    public void afterCompletion(JoinPoint joinPoint) {
        long executionTime = System.currentTimeMillis() - start;

        log.info("Method: " + joinPoint.getSignature() + " executed in " + executionTime + "ms");
        log.info("==============End Logging==============");
    }

}
