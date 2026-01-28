package com.yonsai.books;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class BookController {

    @GetMapping("/test")
    public ResponseEntity<LocalDateTime> test () {
        LocalDateTime now = LocalDateTime.now();
        return ResponseEntity.ok(now);
    }
}
