package com.epam.commons.logger.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;

import static com.epam.commons.logger.aop.Logger.write;
import static java.lang.String.format;

/**
 * Created by Roman_Iovlev on 7/30/2017.
 */
@SuppressWarnings("unused")
@Aspect
public class LogAspects {
    @Pointcut("@annotation(com.epam.commons.logger.aop.Log)")
    public void withStepAnnotation() {
        //pointcut body, should be empty
    }

    @Pointcut("execution(* *(..))")
    public void anyMethod() {
        //pointcut body, should be empty
    }

    @Before("anyMethod() && withStepAnnotation()")
    public void stepStart(JoinPoint joinPoint) {
        write(format("Before step '%s'", createTitle(joinPoint)));
    }

    @AfterThrowing(pointcut = "anyMethod() && withStepAnnotation()", throwing = "e")
    public void stepFailed(JoinPoint joinPoint, Throwable e) {
        write(format("Log '%s' failed with exception '%s'", createTitle(joinPoint), e.getMessage()));
    }

    @AfterReturning(pointcut = "anyMethod() && withStepAnnotation()", returning = "result")
    public void stepStop(JoinPoint joinPoint, Object result) {
        write(format("After step '%s'", createTitle(joinPoint)));
    }

    private String createTitle(JoinPoint joinPoint) {
        MethodSignature method = (MethodSignature) joinPoint.getSignature();
        Log log = method.getMethod().getAnnotation(Log.class);
        if (log == null) return "";
        String title = log.value();
        return title.isEmpty()
                ? method.getName()
                : title;
    }
}
