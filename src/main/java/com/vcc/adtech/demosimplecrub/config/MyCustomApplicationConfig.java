package com.vcc.adtech.demosimplecrub.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class MyCustomApplicationConfig implements WebServerFactoryCustomizer<JettyServletWebServerFactory>, WebMvcConfigurer {

    private final Settings setting = Settings.getInstance();

    @Autowired
    private MyCustomRequestInterceptor customRequestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);

//        registry.addInterceptor(customRequestInterceptor).excludePathPatterns("/**");
        registry.addInterceptor(customRequestInterceptor).addPathPatterns("/**").excludePathPatterns("/error", "/ping");
    }

    @Override
    public void customize(JettyServletWebServerFactory factory) {
        factory.setContextPath(setting.PREFIX_PATH);
        factory.setPort(setting.PORT);
    }

    @Bean
    public ExecutorService poolTaskExecutor() {
        return Executors.newCachedThreadPool(new ThreadFactoryBuilder().setNameFormat("singleton-pool-%d").build());
    }

}
