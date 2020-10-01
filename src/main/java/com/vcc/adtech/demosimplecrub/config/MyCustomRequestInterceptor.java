package com.vcc.adtech.demosimplecrub.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;

@Component
public class MyCustomRequestInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(MyCustomRequestInterceptor.class);

    public static String makeUrl(HttpServletRequest request) {
        StringBuilder builder = new StringBuilder(request.getRequestURL().toString());
        if (request.getQueryString() != null && !request.getQueryString().trim().isEmpty()) {
            builder.append("?").append(request.getQueryString());
        }
        return builder.toString();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        long startTime = Instant.now().toEpochMilli();
//        logger.info("\n[" + request.getMethod() + "] Request URL:: " + makeUrl(request) + " :: Start Time=" + new Date() + "\n");
        request.setAttribute("startTime", startTime);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) {
        long startTime = (Long) request.getAttribute("startTime");
        logger.info("\n[" + request.getMethod() + "] Request URL:: " + makeUrl(request) + " :: Time Taken="
                + (Instant.now().toEpochMilli() - startTime) + "\n");
    }
}
