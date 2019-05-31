package com.cxd.cool.util;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.cxd.cool.domain.MonitorBean;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
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
    public void logPrint() {
    }

    /**
     * 拦截方法上有logPrint注解的
     *
     * @param joinPoint
     * @param logPrint
     */
    //@Before("logPrint() && @annotation(logPrint)")
    public void printLog(ProceedingJoinPoint joinPoint, LogPrint logPrint) {
        try {
            logger.info("********************value={}****************", logPrint.value());
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
        HttpServletRequest request = null;
        StringBuffer sBuffer = null;
        String url = null;
        int port = -1;
        Object object = null;
        String methodName = null;

        long startTime = System.currentTimeMillis();
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            request = attributes.getRequest();
            url = request.getRequestURL().toString();
            port = request.getRemotePort();

            // 拦截的实体类
            Object target = joinPoint.getTarget();
            methodName = joinPoint.getSignature().getName();
            Object[] argsa = joinPoint.getArgs();
            Class[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
            Method method = target.getClass().getMethod(methodName, parameterTypes);

            // 组织参数列表
            sBuffer = new StringBuffer();
            for (Object arg : argsa) {
                sBuffer.append(JSON.toJSONString(arg)).append(" | ");
            }
            object = joinPoint.proceed();

        } catch (Throwable e) {
            if (e instanceof BusinessException) {
                throw e;
            } else {
                throw new BusinessException(2, "未知异常");
            }
        } finally {
            //封装请求日志
            MonitorBean bean = new MonitorBean();
            bean.setIp(ChenxdUtil.getIpAddr(request));
            bean.setMethod(methodName);
            bean.setParams(sBuffer.toString());
            bean.setPort(port + "");
            bean.setTimes((System.currentTimeMillis() - startTime) + "");
            bean.setUrl(url);
            logger.info("Around end--------{}", JSON.toJSONString(bean));
        }

        // around需要返回结果
        return object;
    }
}
