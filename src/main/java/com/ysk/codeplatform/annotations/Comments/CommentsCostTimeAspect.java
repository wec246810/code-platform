package com.ysk.codeplatform.annotations.Comments;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Log4j2
public class CommentsCostTimeAspect {
    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.nanoTime();
        Object result = pjp.proceed();
        long end = System.nanoTime();
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        Comments comments = targetMethod.getAnnotation(Comments.class);

        long msValue = (end - begin) / 1000000;
        if (msValue < 300) {
            log.info(" {} 消耗时长 {} ms",
                    comments.value(),
                    msValue);
        } else {
            log.warn(" {} 消耗时长 {} ms",
                    comments.value(),
                    msValue);
        }

        return result;
    }

    @Pointcut("@annotation(com.lsj.bushubao.common.annotation.Comments)")
    private void pointCutMethod() {
    }
}