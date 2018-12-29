package com.cxd.cool.config;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cxd.cool.domain.RabbitConfigDomain;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private List<ChannelAwareMessageListener> messageListener;

    @Bean("taskProcessExecutor")
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(20);
        taskExecutor.setMaxPoolSize(50);
        taskExecutor.setQueueCapacity(6000);
        taskExecutor.setKeepAliveSeconds(300);
        taskExecutor.setThreadNamePrefix("collecte-");
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Bean
    public RabbitConfigDomain rabbitConfigDomain(ConnectionFactory connectionFactory) {
        RabbitConfigDomain rabbitConfigDomain = new RabbitConfigDomain();
        rabbitConfigDomain.setMessageListeners(messageListener);
        rabbitConfigDomain.setConnectionFactory(connectionFactory);
        return rabbitConfigDomain;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {

            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                return HandlerInterceptor.super.preHandle(request, response, handler);
            }

            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {}

            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {}

        }).addPathPatterns("/**");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        /**
         * FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
         * FastJsonConfig config = new FastJsonConfig();
         * config.setSerializerFeatures(SerializerFeature.WriteMapNullValue);//保留空的字段
         * //SerializerFeature.WriteNullStringAsEmpty,//String null -> ""
         * //SerializerFeature.WriteNullNumberAsZero//Number null -> 0
         * // 按需配置，更多参考FastJson文档哈
         * converter.setFastJsonConfig(config);
         * converter.setDefaultCharset(Charset.forName("UTF-8"));
         * converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
         * converters.add(converter);
         */
        System.out.println("----------configureMessageConverters-----------");
    }

    /**
     * toLogin-->login.jsp
     * 
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/toLogin").setViewName("users/login");
        registry.addViewController("/test").setViewName("test");
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

}
