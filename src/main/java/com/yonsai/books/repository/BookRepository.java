package com.yonsai.books.repository;

import com.yonsai.books.domain.Book;
import com.yonsai.books.domain.SellStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsByTitleAndAuthorAndSellStatus(String title, String author, SellStatus sellStatus);
}
