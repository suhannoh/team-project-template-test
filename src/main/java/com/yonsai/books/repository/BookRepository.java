package com.yonsai.books.repository;

import com.yonsai.books.domain.Book;
import com.yonsai.books.domain.SellStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Book 엔티티를 위한 JPA Repository
 * @author 노수한
 */
public interface BookRepository extends JpaRepository<Book, Long> {
    /**
     * 제목 , 저자 , 판매상태를 기준으로 중복 확인
     * @param title 제목
     * @param author 저자
     * @param sellStatus 판매상태
     * @return boolean 중복 확인 결과
     */
    boolean existsByTitleAndAuthorAndSellStatus(String title, String author, SellStatus sellStatus);

    Optional<Book> findByBookId(Long bookId);
}
