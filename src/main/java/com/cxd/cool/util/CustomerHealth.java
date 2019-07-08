package com.cxd.cool.util;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * 自定义监控
 */
@Component
public class CustomerHealth extends AbstractHealthIndicator {
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        builder.up().status("888").withDetail("cxd", "this is test").withDetail("cxd2", "look up");
    }
}
