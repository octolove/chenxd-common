package com.cxd.cool.util;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cxd.cool.annotation.LogPrint;
import com.cxd.cool.base.BusinessException;

@Aspect
@Component
public class AspectCommon {

    private Logger logger = LoggerFactory.getLogger(AspectCommon.class);

    @Pointcut("@annotation(com.cxd.cool.annotation.LogPrint)")
    public void logPrint() {}

    /**
     * 拦截方法上有logPrint注解的
     * 
     * @param joinPoint
     * @param logPrint
     */
    @Around("logPrint() && @annotation(logPrint)")
    public void printLog(ProceedingJoinPoint joinPoint, LogPrint logPrint) {
        try {
            logger.info("---------->logPrint value={}", logPrint.value());
            joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 每个方法时间统计
     * 
     * @throws Throwable
     */
    @Around("execution(* com.cxd.cool..*.*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object executeTtime(ProceedingJoinPoint joinPoint) throws Throwable {
        //logger.info(">>>>>>>>>>>time bengin");
        Object object = null;
        long startTime = System.currentTimeMillis();
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();

            // 拦截的实体类
            Object target = joinPoint.getTarget();
            String methodName = joinPoint.getSignature().getName();
            Object[] argsa = joinPoint.getArgs();
            Class[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
            Method method = target.getClass().getMethod(methodName, parameterTypes);

            // 组织参数列表
            StringBuffer sBuffer = new StringBuffer();
            for (Object arg : argsa) {
                sBuffer.append(arg.toString()).append("");
            }

            //logger.info(">>>>>>>>methodName={},args={}", methodName, sBuffer.toString());
            object = joinPoint.proceed();

        } catch (Throwable e) {
            if (e instanceof BusinessException) {
                throw e;
            } else {
                logger.error("error:" + e);
            }
        } finally {
            logger.info(">>>>>>>>>time bengin execute time={}", (System.currentTimeMillis() - startTime));
        }

        // around需要返回结果
        return object;
    }
}
