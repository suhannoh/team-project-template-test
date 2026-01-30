package com.yonsai.books;

import com.yonsai.books.domain.Book;
import com.yonsai.books.domain.Cart;
import com.yonsai.books.dto.BookAddRequest;
import com.yonsai.books.repository.BookRepository;
import com.yonsai.books.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CartControlllerTest {
  @Autowired
  MockMvc mockMvc;

  @Test
  void 카트_컨트롤러_비로그인_테스트 () throws Exception {
    mockMvc.perform(post("/cart/add")
            .param("bookId", "1") // 도서번호
            .param("quantity", "1")) // 수량
            // 로그인 안 했으므로 인터셉터가 리다이렉트해야 함
            .andExpect(status().is3xxRedirection())
            // 로그인 페이지로 이동하는지 확인
            .andExpect(redirectedUrl("/login"));
  }

  @Test
  void 카트_컨트롤러_로그인_테스트() throws Exception {
    MockHttpSession session = new MockHttpSession();
    session.setAttribute("userName", "tester");

    mockMvc.perform(post("/cart/add")
                    .session(session)
                    .param("bookId", "1")
                    .param("quantity", "1"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/cart"));
  }

  @Autowired
  CartService cartService;
  @Autowired
  BookRepository bookRepository;

  @Test
  void 같은_책_두번_추가하면_수량만_증가() {
    // given
    String user = "tester";
    Book book = bookRepository.save(
            new Book(new BookAddRequest("소설","제목","작가","설명",1000,0,100,10))
    );

    // when
    // 같은 책을 2번 추가
    cartService.addByBook(user, book.getBookId(), 1);
    cartService.addByBook(user, book.getBookId(), 1);

    Cart cart = cartService.getCart(user);

    // then
    assertEquals(1, cart.getItem().size()); // 아이템은 1개
    assertEquals(2, cart.getItem().get(0).getQuantity()); // 수량은 2개면 OK

  }
}
