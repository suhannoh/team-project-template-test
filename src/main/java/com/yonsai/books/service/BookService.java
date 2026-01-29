package com.yonsai.books.service;

import com.yonsai.books.domain.Book;
import com.yonsai.books.domain.SellStatus;
import com.yonsai.books.dto.BookSelectRequest;
import com.yonsai.books.dto.BookSelectResponse;
import com.yonsai.books.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 각 키워드에 따른 도서 조회
     * @param req
     * @return
     */
    public List<BookSelectResponse> findBySelectKeyword (BookSelectRequest req) {
        validateSelectCondition(req);

        List<Book> books = bookRepository.findBy(
                emptyToNull(req.keyword()),
                emptyToNull(req.author()),
                emptyToNull(req.category()),
                req.status(),
                req.price()
        );
        return books.stream()
                .map(BookSelectResponse::create)
                .toList();

    }

    /**
     *  빈 공백도 null로 변환하는 메서드
     *    - null 혹은 공백인 상황도 null로 변환하여
     *    - 쿼리문에서 null 값으로 사용할 수 있게한다
     * @param v
     * @return
     */
    private String emptyToNull(String v) {
        return (v == null || v.isBlank()) ? null : v;
    }
    /**
     *
     * 사용자로부터 받아온 검색 키워드를 검증하는 메서드
     *  조건 1 : 하나의 키워드라도 존재해야 함
     *  조건 2 : 판매 상태는 ENUM으로 존재하는 키워드여야함
     *  조건 3 : todo category도 ENUM으로 변경하면 존재하는 키워드여야함
     *  
     * @param req
     */
    private void validateSelectCondition (BookSelectRequest req) {
        boolean isValid = req.keyword() != null && !req.keyword().isBlank()
                       || req.author() != null && !req.author().isBlank()
                       || req.author() != null && !req.category().isBlank()
                       || req.price() != null
                       || req.status() != null;

        if(!isValid) {
            throw new IllegalArgumentException("검색 조건을 하나 이상 입력해주세요");
        }

        if(!(req.status() == SellStatus.IN_STOCK || req.status() == SellStatus.SOLD_OUT)) {
            throw new IllegalArgumentException("판매 상태는 SOLD_OUT / IN_STOCK 두 상태로 이루어져있습니다 . 다시 시도해주세요");
        }
    }


}
