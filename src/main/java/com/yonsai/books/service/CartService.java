package com.yonsai.books.service;

import com.yonsai.books.domain.Book;
import com.yonsai.books.domain.Cart;
import com.yonsai.books.domain.CartItem;
import com.yonsai.books.repository.BookRepository;
import com.yonsai.books.repository.CartMemoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

  private final CartMemoryRepository cartMemoryRepository;
  private final BookRepository bookRepository;

  /**
   * 장바구니에 도서를 추가하는 기능
   *  - 같은 책은 중복 저장하지 않고 수량만 증가
   *  - 이름 기준 1인 1장바구니
   * @param name 사용자 이름
   * @param bookId 도서 ID
   * @param quantity 수량
   */
  public void addByBook (String name , Long bookId, int quantity) {
    Cart cart = createOrGetCart(name);
    Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("해당 번호에 맞는 도서가 존재하지 않습니다"));

    // 이미 있으면 엔티티 없으면 null
    CartItem cartItem = existCartItem(cart, book);
    // 같은 책이 있으면 수량만 증가
      if (cartItem != null) cartItem.addQuantity(quantity);
    // 없을 때만 새로 만들어서 추가
    else cart.getItem().add(new CartItem(cart, book, quantity));

    cartMemoryRepository.save(cart);
    }

  /**
   * 사용자 이름 기준으로 장바구니 조회
   *  - 이미 존재하면 장바구니 반환
   *  - 없으면 장바구니 생성/저장 후 반환
   *
   * @param name 사용자 이름
   * @return 장바구니
   */
  private Cart createOrGetCart (String name) {
    Cart cart = cartMemoryRepository.findByName(name);
    if (cart == null) {
      cart = new Cart();
      cart.setName(name);
      cartMemoryRepository.save(name,cart);
    }
    return cart;
  }

  /**
   * 장바구니에 같은 도서가 이미 존재하는지 확인
   *   - bookId를 비교하여 동일한 bookId가 있다면 CartItem 반환
   *   - 없으면 null 반환
   * @param cart 장바구니
   * @param book 도서
   * @return CartItem 또는 null
   */
  private CartItem existCartItem (Cart cart , Book book) {
    Long bookId = book.getBookId();
    for (CartItem cartItem : cart.getItem()) {
      if (cartItem.getBook().getBookId().equals(bookId)) {
        return cartItem;
      }
    }
    return null;
  }

  /**
   * 사용자 이름 기준 장바구니 조회 API
   *  - 장바구니 화면을 렌더링할 때 사용자 장바구니 반환용
   * @param name
   * @return
   */
  public Cart getCart(String name) {
    return cartMemoryRepository.findByName(name);
  }
}
