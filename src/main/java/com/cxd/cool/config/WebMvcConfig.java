package com.cxd.cool.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cxd.cool.domain.RabbitConfigDomain;
import com.cxd.cool.util.RequestHandle;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);

    @Autowired
    private List<ChannelAwareMessageListener> messageListener;

    @Bean("taskProcessExecutor")
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(20);
        taskExecutor.setMaxPoolSize(50);
        taskExecutor.setQueueCapacity(6000);
        taskExecutor.setKeepAliveSeconds(300);
        taskExecutor.setThreadNamePrefix("thread-");
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
        // 若要注入redis等bean,不能new出来要从容器拿new RequestHandle()
        // 如果/rest/login这个访问路径,在项目中不存在,那么当你访问http://xxxx//rest/login的时候,依然会被拦截,因为此时变成了error这个路径
        registry.addInterceptor(new RequestHandle()).addPathPatterns("/**").excludePathPatterns("/rest/login");
    }

    /**
     * 静态资源映射
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/myresoure/**").addResourceLocations("classpath:/myresoure/");

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
    }

    /**
     * toLogin-->login.jsp
     * 
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        logger.info(">>>>>>>>>>>>>>>>addViewControllers");
        registry.addViewController("/toLogin").setViewName("users/login");
        registry.addViewController("/test").setViewName("test");
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        logger.info(">>>>>>>>>>>>>>>>configureViewResolvers");
        registry.jsp("/WEB-INF/views/", ".jsp");
    }
}
