package com.yonsai.books.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 *  유저 1 -> Cart 1 -> CartItem N
 */
@Entity
@Getter @Setter
@NoArgsConstructor
public class CartItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // 연관관계 N:1
  // - N개 목록 - 1개 카트
  @ManyToOne
  @JoinColumn(name = "cart_id")
  private Cart cart; // 어떤 카트인지 cart_id

  // 연관관계 N:1
  // - N개 목록 - 1개 책
  @ManyToOne
  @JoinColumn(name = "book_id")
  private Book book; // 어떤 책인지 book_id

  private int quantity; // 몇 권을 골랐는지

  public CartItem(Cart cart, Book book, int quantity) {
    this.cart = cart;
    this.book = book;
    this.quantity = quantity;
  }

  public void addQuantity(int q) {
    this.quantity += q;
  }

}
