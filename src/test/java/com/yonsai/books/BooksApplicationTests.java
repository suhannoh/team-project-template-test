package com.yonsai.books;

import com.yonsai.books.controller.BookController;
import com.yonsai.books.dto.BookAddRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BooksApplicationTests {

    @Autowired
    BookController bookController;

    @Test
    void contextLoads() {
    }

}
