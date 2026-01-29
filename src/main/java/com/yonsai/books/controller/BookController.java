package com.yonsai.books.controller;

import com.yonsai.books.dto.BookUpdateRequest;
import com.yonsai.books.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
     * 도서 수정을 담당하는 API
     * - 도서 ID 와 수정할 데이터를 받아 Service에 전달한다
     * - 이때 전달받는 데이터는 BookUpdateRequest와 매핑하여 전달한다
     *
     * 주의
     *  - 수정이 필요로하는 부분만 전달하면 에러가 날 수 있음
     *  - 수정이 불필요한 부분도 모두 작성 후 전달해야함
     *
     *  todo 수정이 필요한 부분만 받아 null 검사 후 수정할 수 있게 만들 수 있음 ,
     *       지금은 특정 도서 조회 후 수정을 기준으로 하여 수정할 도서의 데이터를 모두 가지고 온다는 가정하에 진행 중
     *       + 수정이 가능한 권한부여 및 검사가 필요함 , 지금은 도서만 생각하여 진행중이라 일단 넘어감
     *
     * @param bookId
     * @param request
     * @return 200 / 400 / 500
     */
    @PostMapping("/book/update/{bookId}")
    public ResponseEntity<Void> updateBook (@PathVariable Long bookId,  @RequestBody @Valid BookUpdateRequest request) {
        bookService.updateByBook(bookId, request);
        return ResponseEntity.ok().build();
    }
}
