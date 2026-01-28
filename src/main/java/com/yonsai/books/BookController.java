package com.yonsai.books;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    public ResponseEntity<Void> test () {

        return ResponseEntity.ok().build();
    }
}
