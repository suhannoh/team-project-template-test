package com.yonsai.books.controller;

import com.yonsai.books.dto.BookAddRequest;
import com.yonsai.books.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


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
     * 도서 추가 요청 API
     * 설명 :
     *  - 도서 정보를 전달받아 기존에 있는지 확인하고 도서를 추가한다
     * @param
     *   request {
     *      category - 카테고리
     *      title - 제목
     *      author - 작성자
     *      description - 설명
     *      price - 가격
     *      discount - 할인율
     *      pages - 페이지 수
     *      stock - 재고 수량
     *   }
     * @return
     *     200 : 저장 성공
     *     400 : 사용자 도서 추가시 유효성 검사 실패
     *     500 : 서버 오류
     */
    @PostMapping("/book/add")
    public ResponseEntity<Void> addBook ( @Valid @RequestBody BookAddRequest request) {
        bookService.findOrCreateBook(request);
        return ResponseEntity.ok().build();
    }

    /**
     * S3 파일 저장 후 Book 저장 요청 API
     */

    @PostMapping("/s3/book")
    public ResponseEntity<Void> s3AddBook (@RequestBody BookAddRequest request) {
        // valid 검증 안 함
        bookService.findOrCreateBook(request);
        return ResponseEntity.ok().build();
    }

}
