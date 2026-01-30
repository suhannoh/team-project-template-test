package com.yonsai.books.repository;

import com.yonsai.books.domain.Cart;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 장바구니 메모리 저장소
 * 역할 : 사용자의 이름을 키값으로 장바구니를 저장한다
 * @author 노수한
 */
@Repository
public class CartMemoryRepository {
  // 메모리 저장소
  private final Map<String, Cart> store = new HashMap<>();

  // 저장 (이름 , 장바구니)
  public void save(String name, Cart cart) {
    store.put(name, cart);
  }
  // 저장 (장바구니)
  public void save(Cart cart) {
    store.put(cart.getName(), cart);
  }
  // 조회 (이름)
  public Cart findByName(String name) {
    return store.get(name);
  }

}
