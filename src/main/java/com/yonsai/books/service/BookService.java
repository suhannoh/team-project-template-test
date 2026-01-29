package com.yonsai.books.service;

import com.yonsai.books.domain.Book;
import com.yonsai.books.dto.BookUpdateRequest;
import com.yonsai.books.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 도서 추가 서비스
 * 역할 :
 *  - 도서 추가 요청을 처리한다
 *
 * 주의 :
 *  - 이미 존재하는 책이면 예외 발생
 */

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    /**
     * 도서 수정 메서드
     *  - Book Id 로 도서 객체를 꺼내
     *  - 객체 내부 메서드를 이용하여 수정을 진행한다
     *
     * 주의
     *  - 넘어오는 request 데이터는 모두 Valid 를 통해 검증 후 들어오기에 이상한 값은 없음
     *  - 수정 전 객체의 데이터와 같은 값이 있을 수 있음
     * @param bookId
     * @param request
     */
    public void updateByBook (Long bookId, BookUpdateRequest request) {
        Book book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new IllegalArgumentException("해당 도서번호의 도서가 존재하지 않습니다."));

        // 도서 수정
        book.patch(request);
        bookRepository.save(book);
    }

}
