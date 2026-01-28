package com.yonsai.books.service;

import com.yonsai.books.domain.Book;
import com.yonsai.books.domain.SellStatus;
import com.yonsai.books.dto.BookAddRequest;
import com.yonsai.books.dto.BookSelectResponse;
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
     * 도서 추가 요청을 처리한다
     *
     * @param request
     * 별도의 리턴 값 없음
     */
    public void findOrCreateBook(BookAddRequest request) {

        // 도서의 제목, 저자, 판매상태가 일치하는 도서가 이미 존재하는지 확인한다
        // 이미 존재하는 책이면 예외 RuntimeException 던지며 종료
        boolean isExists = bookRepository.existsByTitleAndAuthorAndSellStatus(
                                                request.title(), request.author(), SellStatus.IN_STOCK);

        if (isExists) {
            throw new RuntimeException("이미 존재하는 책 입니다");
        }
        //
        createBook(request);
    }

    // 도서를 생성하고 저장한다
    private void createBook(BookAddRequest request) {
        Book book = new Book(request);
        bookRepository.save(book);
        System.out.println("추가한 책 정보 : " + book.toString());
    }


    /**
     * 도서 고유번호를 조회하여 반환한다
     *  - 조회 시 해당 고유번호에 맞는 도서가 없다면 에러를 던진다
     * @param bookId
     * @return
     *  - BookSelectResponse (응답 DTO)
     */
    public BookSelectResponse findByBookIdOrThrow(Long bookId) {
        Book book = bookRepository.findByBookId(bookId)
                      .orElseThrow(() -> new IllegalArgumentException("해당 번호에 맞는 도서가 존재하지 않습니다"));

        return BookSelectResponse.create(book);
    }


}
