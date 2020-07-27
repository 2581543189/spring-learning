package org.example;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * @author zhaohui <zhaohui@kuaishou.com>
 * Created on 2020-07-27
 */
@Aspect
@Component
public class Profiling {

    @Pointcut("execution(void org.example.service..a()) " +
            "||execution(void org.example.service..b()) " +
            "||execution(void org.example.service..c()) ")
    private void ptc() {
    }


    @Around("org.example.Profiling.ptc()")
    public void doPtc(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start();
        pjp.proceed();
        sw.stop();
        System.out.println("方法" + pjp.getSignature().getName() + "耗时[" + (sw.getTotalTimeMillis()) + "]ms");
    }
}
