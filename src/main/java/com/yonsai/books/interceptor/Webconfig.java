package com.yonsai.books.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class Webconfig implements WebMvcConfigurer {

    private final LoginCheckInterceptor loginCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor) // 인터셉터 등록
                .addPathPatterns("/cart/**") // 인터셉터 적용 경로
                .excludePathPatterns("/login","/reg"); // 제외 경로
    }
}
