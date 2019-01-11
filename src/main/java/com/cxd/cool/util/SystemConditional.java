package com.cxd.cool.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

import com.cxd.cool.domain.UserInfo;

/**
 * 条件判断
 * 根据不同的操作系统创建不同的对象
 */
@Component
public class SystemConditional {

    @Conditional(WindowsCondition.class)
    @Bean
    public UserInfo getWin() {
        System.out.println("-------------------------->window");
        UserInfo userInfo = new UserInfo();
        userInfo.setUname("window");
        return userInfo;
    }

    @Conditional(LinuxCondition.class)
    @Bean
    public UserInfo getLinux() {
        System.out.println("-------------------------->linux");
        UserInfo userInfo = new UserInfo();
        userInfo.setUname("linux");
        return userInfo;
    }
}


class WindowsCondition implements Condition {
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        String osName = environment.getProperty("os.name");
        return osName.contains("Windows");
    }
}


class LinuxCondition implements Condition {
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        String osName = environment.getProperty("os.name");
        return osName.contains("Linux");
    }
}
