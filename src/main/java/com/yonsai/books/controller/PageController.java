package com.yonsai.books.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * View 를 담당하는 컨트롤러
 * 역할 : 호출에 맞는 뷰를 반환한다.
 * @author 노수한
 */
@Controller
public class PageController {

  // 메인
  @GetMapping("/")
  public String home (Model model) {

    //todo 처음 조회를 하며 리스트를 가져와 전달한다
    return "index";
  }

  // 로그인
  @GetMapping("/login")
  public String login () {

    return "login";
  }


}
