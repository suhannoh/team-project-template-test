package com.yonsai.books.controller;

import com.yonsai.books.dto.BookAddRequest;
import com.yonsai.books.service.BookAddService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookAddService bookAddService;

    // 책 추가 메인 메서드
    @PostMapping("/book/add")
    public ResponseEntity<Void> addBook ( @Valid @RequestBody BookAddRequest request) {
        bookAddService.findOrCreateBook(request);
        return ResponseEntity.ok().build();
    }

    // IllegalArgumentException 발생
    @GetMapping("/test/illegal")
    public void testException1() {
        throw new IllegalArgumentException("TEST IllegalArgumentException");
    }

    // RuntimeException 발생
    @GetMapping("/test/runtime")
    public void testException2() {
        throw new RuntimeException("TEST RuntimeException");
    }
}
