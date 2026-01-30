package com.yonsai.books.controller;

import com.yonsai.books.domain.Cart;
import com.yonsai.books.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 장바구니 컨트롤러
 * 역할 : 사용자의 장바구니 관련 API 컨트롤러
 * 권한인증 : cart로 시작되는 모든 요청을 인터셉트에서 로그인 여부를 확인함
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

  private final CartService cartService;

  /**
   * 장바구니 추가 API
   *   - 도서 번호와 수량을 전달받아 장바구니에 담는다
   * @param session 세션
   * @param bookId 도서 ID
   * @param quantity 수량
   * @return redirec:/cart 로 이동
   */
  @PostMapping("/add")
  public String add(HttpSession session,
                    @RequestParam Long bookId,
                    @RequestParam int quantity) {

    // 장바구니는 세션에 저장된 userName으로 생성 혹은 조회한다
    String userId = (String) session.getAttribute("userName");
    cartService.addByBook(userId, bookId, quantity);

    return "redirect:/cart";
  }

  /**
   * 장바구니페이지 렌더링
   * - 장바구니 조회 후 model에 저장후 렌더링한다
   * @param session 세션
   * @param model 모델
   * @return 장바구니 페이지로 이동
   */
  @GetMapping
  public String cartPage(HttpSession session, Model model) {
    String name = (String) session.getAttribute("userName");

    // 내 이름으로 되어있는 장바구니 호출
    Cart cart = cartService.getCart(name);
    model.addAttribute("cart", cart);

    return "cart";
  }
}
