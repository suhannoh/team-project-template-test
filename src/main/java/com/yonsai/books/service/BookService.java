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
 * 도서 서비스
 * 역할 :
 *  - 도서에 관련된 모든 비지니스 로직을 처리한다
 *  - 예외 발생시 모든 예외는 GlobalExceptionHandler에서 처리한다
 *
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
     * 각 키워드에 따른 도서 조회하는 메서드
     *  - 키워드 별 검색 가능
     *  - 키워드를 묶어 종합 검색 가능 (and 조건)
     *
     * 주의
     *  - 키워드가 하나도 없이 요청하면 400 에러
     *  - Keyword는 Like문을 사용하여 중간 검색이가능
     *  - 그 외에 카테고리 작성자는 정확한 검색어를 입력받아야함
     *  - Status는 Book 객체 SellStatus에 맞게
     *
     * @param req
     * @return
     *  List<BookSelectResponse>
     *
     * todo - 현재는 List 로 반환하고 있지만 추후에
     *        목적에 따라 Pageable 사용 예정
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

        // 사용자에게 전달할 DTO 형태로 변환
        return books.stream()
                .map(BookSelectResponse::create)
                .toList();

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
        //
        boolean isValid = isNotnull(req.keyword())
                       || isNotnull(req.category())
                       || isNotnull(req.author())
                       || req.price() != null
                       || req.status() != null;

        if(!isValid) {
            throw new IllegalArgumentException("검색 조건을 하나 이상 입력해주세요");
        }
    }

    /**
     * String 값이 null 혹은 공백인지 알려주는 유틸 메서드
     * @param s
     * @return boolean
     */
    private boolean isNotnull (String s) {
        return s != null && !s.isBlank();
    }
    /**
     *  빈 공백도 null로 변환하는 유틸 메서드
     *    - null 혹은 공백인 상황도 null로 변환하여
     *    - 쿼리문에서 null 값으로 사용할 수 있게한다
     * @param v
     * @return v or Null
     */
    private String emptyToNull(String v) {
        return (v == null || v.isBlank()) ? null : v;
    }

}
