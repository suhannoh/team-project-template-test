package com.yonsai.books.dto;

import com.yonsai.books.domain.Book;
import com.yonsai.books.domain.SellStatus;

import javax.validation.constraints.*;

/**
 * 도서 선택 응답 DTO
 * @author 노수한
 */
public record BookSelectResponse(
        Long bookId,
        String category,
        String title,
        String author,
        String description,
        int price,
        int discount,
        int pages,
        int stock,
        SellStatus sellStatus
) {

    /**
     * 도서 엔티티를 도서 선택 응답 DTO로 변환한다
     * @param book 엔티티 그대로 받는다
     * @return 도서 선택 응답 DTO로 매핑하여 반환
     */
    public static BookSelectResponse create (Book book)
    {
        return new BookSelectResponse(
                book.getBookId(),
                book.getCategory(),
                book.getTitle(),
                book.getAuthor(),
                book.getDescription(),
                book.getPrice(),
                book.getDiscount(),
                book.getPages(),
                book.getStock(),
                book.getSellStatus()
        );
    }
}
