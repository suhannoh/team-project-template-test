package com.yonsai.books.controller;

import com.yonsai.books.dto.UserRegisterRequest;
import com.yonsai.books.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@Controller
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  public String login (HttpSession session, @RequestParam String name) {
    authService.createOrGetUser(session.getId(), name);
    session.setAttribute("userId", name);
    return "redirect:/";
  }

  @GetMapping("/me")
  public ResponseEntity<String> me(HttpSession session) {
    Object name = session.getAttribute("userId");
    if (name == null) return ResponseEntity.status(401).build();
    return ResponseEntity.ok((String) name);
  }

  @PostMapping("/logout")
  public ResponseEntity<Void> logout(HttpSession session) {
    session.invalidate();
    return ResponseEntity.ok().build();
  }
}
