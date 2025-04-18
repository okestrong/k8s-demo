package com.kevin.config;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
class StartupDelay {
    @PostConstruct
    public void init() throws InterruptedException {
        Thread.sleep(30000); // 30초 지연
        System.out.println("Application startup delay completed.");
    }
}

@Configuration
class StartupInterceptorConfig implements WebMvcConfigurer, ApplicationListener<ContextRefreshedEvent> {

    private volatile boolean isApplicationReady = false;

    @Value("${server.servlet.context-path:/}")
    private String contextPath;

    @Override
    public void onApplicationEvent(@NotNull ContextRefreshedEvent event) {
        isApplicationReady = true;
        System.out.println("어플리케이션 시동 완료 !!");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
                if (!isApplicationReady) {
                    response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                    response.getWriter().write("##### 접속 실패!! Application 이 시작중입니다. 잠시 후 다시 시도하세요 #####");
                    return false;
                }
                return true;
            }
        }).addPathPatterns("/**").excludePathPatterns(contextPath);
    }
}
