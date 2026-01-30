package com.yonsai.books.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *  로그인 인터셉터
 *   - 로그인 시 세션에 userName이라는 키 값으로 이름을 저장함
 *   - 이 userName을 세션에서 확인하여 있으면 메서드 실행
 *   - null 이면 로그인 페이지로 이동한다
 *
 */
@Component
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    log.info("인터셉터 실행 (로그인 확인)");
    HttpSession session = request.getSession(false);

    if (session == null || session.getAttribute("userName") == null) {
      log.warn("인터셉터 실행 (로그인 확인 실패)");
      response.sendRedirect("/login");
      return false;
    }
    log.info("인터셉터 실행 (로그인 확인 완료)");
    return true;
  }
}
