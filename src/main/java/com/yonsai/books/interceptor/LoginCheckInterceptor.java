package com.yonsai.books.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    log.info("preHandle");
    //
    HttpSession session = request.getSession(false);

    if (session == null || session.getAttribute("userId") == null) {
      response.sendRedirect("/login");
      return false;
    }

    return true;
  }

}
