package com.cxd.cool.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogPrintAspect {

    private Logger logger = LoggerFactory.getLogger(LogPrintAspect.class);

    @Pointcut("@annotation(com.cxd.cool.annotation.LogPrint)")
    public void logPrint() {

    }

    @Around("logPrint()")
    public Object print(ProceedingJoinPoint point) {
        long beginTime = System.currentTimeMillis();
        // 执行方法
        Object result = null;
        try {
            result = point.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        logger.info("--------------------time------------->>>>>" + (time - beginTime));

        return result;
    }

}
