package com.yonsai.books.repository;

import com.yonsai.books.domain.Book;
import com.yonsai.books.domain.SellStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BookRepository extends JpaRepository<Book, Long> {
    // 제목 , 저자 , 판매상태를 기준으로 중복 확인
    boolean existsByTitleAndAuthorAndSellStatus(String title, String author, SellStatus sellStatus);
}
