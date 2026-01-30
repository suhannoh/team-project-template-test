package com.yonsai.books.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *  인터셉터 설정
 *  -  로그인하지 않은 사용자는 장바구니 접근 불가
 *
 *      - 현재 적용하는 인터셉터 경로는 "cart/**"
 *      - 장바구니 관련되는 매핑되는 주소를 모두 포함시켰음
 *
 *      - 제외
 *          - 로그인
 */
@Configuration
@RequiredArgsConstructor
public class Webconfig implements WebMvcConfigurer {

    private final LoginCheckInterceptor loginCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor) // 인터셉터 등록
                .addPathPatterns("/cart/**") // 인터셉터 적용 경로 지금은 cart 시작하는 것만 검사
                .excludePathPatterns("/login", "/"); // 제외 경로
    }
}
