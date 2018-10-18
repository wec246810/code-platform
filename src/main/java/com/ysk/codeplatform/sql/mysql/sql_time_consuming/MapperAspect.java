package com.ysk.codeplatform.sql.mysql.sql_time_consuming;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 描述
 * 打印sql执行时间
 * <p>
 * <p>
 * <!--aop依赖-->
 * <dependency>
 * <groupId>org.springframework.boot</groupId>
 * <artifactId>spring-boot-starter-aop</artifactId>
 * </dependency>
 *
 * @author Y.S.K
 * @date 2018/8/13 9:35
 */
@Aspect
@Component
@Log4j2
@Profile("dev")
public class MapperAspect {

    public static void main(String[] args) {

    }

    @AfterReturning("pointCutMethod()")
    public void logServiceAccess() {
    }


    @Pointcut("execution(* com.lsj.shushubuyue.dao.*Mapper.*(..))")
    private void pointCutMethod() {
    }

    /**
     * 声明环绕通知
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.nanoTime();
        Object obj = pjp.proceed();
        long end = System.nanoTime();
        log.info("调用Mapper方法：{}，参数：{}，耗时：{}毫秒",
                pjp.getSignature().toString(), Arrays.toString(pjp.getArgs()),
                (end - begin) / 1000000);
        return obj;
    }
}
