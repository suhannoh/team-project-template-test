package com.yonsai.books.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class CartController {

  @GetMapping("/cart")
  public String myCart (HttpSession session) {

    System.out.println(session.getAttribute("userId"));

    return "로그인 성공 및 장바구니 페이지";
  }
}
