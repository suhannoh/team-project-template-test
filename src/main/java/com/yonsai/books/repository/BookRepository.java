package com.yonsai.books.repository;

import com.yonsai.books.domain.Book;
import com.yonsai.books.domain.SellStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
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


    /**
     * 도서 검색을 위한 조건을 받아 직접 쿼리문을 작성한다
     * @Query 안의 :paramName 은
     * 메서드 파라미터 이름(@Param("paramName"))과 매핑된다.
     * SELECT 문을 이용하여 넘어온 파라미터의 값이
     * null이면 무시하며 값이 있는 경우 조회를 진행한다 ㄴ
     *  - 조회하는 b 는 Book 객체를 뜻한다
     *  - SQL 로는 select * 와 같다
     * @param keyword
     * @param author
     * @param category
     * @param status
     * @param price
     * @return
     */
    @Query("""
    select b from Book b
    where (:keyword is null or b.title like %:keyword%)
      and (:author is null or b.author = :author)
      and (:category is null or b.category = :category)
      and (:status is null or b.sellStatus = :status)
      and (:price is null or b.price <= :price)
    """)
    List<Book> findBy(
            @Param("keyword") String keyword,
            @Param("author") String author,
            @Param("category") String category,
            @Param("status") SellStatus status,
            @Param("price") Integer price
    );
}
