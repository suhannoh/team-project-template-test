package com.yonsai.books.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 장바구니 엔티티
 */
@Entity
@Getter @Setter
@NoArgsConstructor
public class Cart {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long cartId;
  // 사용자 이름으로 구분
  private String name;

  // 실제 담기는 책 목록
  // 1:N 관계
  // - 1개 장바구니 - N개 책
  @OneToMany(mappedBy = "cart")
  private List<CartItem> item = new ArrayList<>();
}
