package com.yonsai.books.controller;

import com.yonsai.books.dto.BookAddRequest;
import com.yonsai.books.dto.BookSelectRequest;
import com.yonsai.books.dto.BookSelectResponse;
import com.yonsai.books.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * 도서 관련 API를 처리하는 컨트롤러
 * 역할 :
 *  - 도서 관련 CRUD 요청을 받는다
 *  - 비지니스 로직은 Service로 위임한다.
 *
 * @Author 노수한
 *
 */
@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    /**
     * 도서 고유번호 조회 요청 API
     * 설명 :
     *  - 도서 고유번호를 PathVariable로 꺼내서 Service로 전달한다
     *  - 전달받은 고유번호로 도서 조회를 하고
     *  - 조회 결과를 BookSelectReponse DTO로 변환하여 사용자에게 전달한다
     * @param bookId
     * @return BookSelectResponse Json
     */
    @GetMapping("/book/get/{bookId}")
    public ResponseEntity<BookSelectResponse> getBook (@PathVariable Long bookId) {
        BookSelectResponse res = bookService.findByBookIdOrThrow(bookId);
        return ResponseEntity.ok(res);
    }

    /**
     *  도서 특정 키워드 검색 API
     *    - 검색 키워드 종류
     *       - 도서명 특정 키워드
     *       - 특정 카테고리
     *       - 특정 가격 이하
     *       - 판매 상태에 따른 조회
     *       - 작가 이름
     *  설명 :
     *   - 특정 키워드를 담은 Request DTO와 매핑하여 Service로 전달한다
     *   - 전달받은 키워드를 검증 후 조회하고
     *   - 조회 결과를 BookSelectResponse DTO로 변환하여 List에 담아 사용자에게 전달한다
     *
     * @param request
     *  BookSelectRequest
     *
     * @return
     *  - 조회 결과를 List 형태로 반환
     */
    @PostMapping("/book/get")
    public ResponseEntity<List<BookSelectResponse>> findByKeyword (
            @Valid @RequestBody BookSelectRequest request
    ) {
        List<BookSelectResponse> res = bookService.findBySelectKeyword(request);

        return ResponseEntity.ok(res);
    }

}
