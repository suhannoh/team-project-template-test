package com.yonsai.books.service;

import com.yonsai.books.domain.Book;
import com.yonsai.books.domain.SellStatus;
import com.yonsai.books.dto.BookAddRequest;
import com.yonsai.books.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookAddService {

    private final BookRepository bookRepository;

    // 이미 존재하는 책이면 예외 발생
    public void findOrCreateBook(BookAddRequest request) {
        boolean isExists = bookRepository.existsByTitleAndAuthorAndSellStatus(
                                                request.title(), request.author(), SellStatus.IN_STOCK);
        if (isExists) {
            throw new RuntimeException("이미 존재하는 책 입니다");
        }

        createBook(request);
    }

    // 객체 생성 및 저장
    private void createBook(BookAddRequest request) {
        Book book = new Book(request);
        bookRepository.save(book);
        System.out.println("추가한 책 정보 : " + book.toString());
    }
}
