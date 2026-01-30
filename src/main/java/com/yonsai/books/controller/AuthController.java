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

/**
 *  로그인 및 상태 컨트롤러
 *  역할 : 세션과 이름을 전달받아 Service로 위임한다.
 *  주의 : 메모리 저장이라 서버 종료시 모두 삭제
 *    -
 */

@Controller
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  /**
   *  로그인 요청 API
   *  - 별도의 가입 없이 이름만 전달받아 메모리에 임시로 저장하여 사용
   *  - 메모리 공간에 저장 후 세션에 이름 저장하여 확인함
   * @param session
   * @param name
   * @return 메인페이지 렌더링
   */
  @PostMapping("/login/register")
  public String login (HttpSession session, @RequestParam String name) {
    // 저장소에 추가 및 세션에 저장
    authService.createOrGetUser(session.getId(), name);
    session.setAttribute("userName", name);
    return "redirect:/";
  }

  /**
   * 로그인 여부 확인 요청 API
   * - 세션에 저장되어있는 userId를 확인하여 존재하면 200 응답을 반환
   * - 메인페이지에서 이 API를 통해 로그인/ 로그아웃 버튼을 변경한다
   * @param session
   * @return 200
   */
  @GetMapping("/me")
  public ResponseEntity<String> me(HttpSession session) {
    Object name = session.getAttribute("userName");
    if (name == null) return ResponseEntity.status(401).build();
    return ResponseEntity.ok((String) name);
  }

  /**
   * 로그아웃 요청 API
   * - 세션에 저장되어 있는 데이터를 모두 삭제한다
   * @param session
   * @return 메인페이지 렌더링
   */
  @GetMapping("/logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/";
  }
}
