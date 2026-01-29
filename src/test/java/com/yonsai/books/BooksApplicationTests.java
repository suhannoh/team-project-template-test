package com.yonsai.books;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BooksApplicationTests {

  @Autowired
  MockMvc mockMvc;

  @Test
  void 책_추가_컨트롤러_테스트 () throws Exception {
    //given 테스트 데이터..

    // 자바 15+의 텍스트 블록 문법
    // String 값만 제대로 된 JSON이면 OK
    String jsonRequest = """
        {
          "category": "소설",
          "title": "스프링 부트 완벽 가이드 테스트 44",
          "author": "노수한",
          "description": "JPA와 스프링 부트를 활용한 책 관리 프로젝트 예제입니다.",
          "price": 15000,
          "discount": 20,
          "pages": 320,
          "stock": 10
        }
        """;

    // when && then
    // json 보내고 응답 코드 확인
    mockMvc.perform(post("/book/add")
            .contentType(MediaType.APPLICATION_JSON) // json 데이터를 전달할 때 사용
            .content(jsonRequest)) // json 테스트 데이터
            .andExpect(status().isOk()); // 상태값이 200이면 ok
  }

  @Test
  void DTO_VALID_ERROR () throws Exception {
    String jsonRequest = """
        {
          "category": "소설",
          "title": "스프링 부트 완벽 가이드 테스트 44",
          "author": "노수한",
          "description": "JPA와 스프링 부트를 활용한 책 관리 프로젝트 예제입니다.",
          "price": 15000,
          "discount": 20,
          "pages": 320,
          "stock": 10
        }
        """;

    mockMvc.perform(post("/book/add")
                    .contentType(MediaType.APPLICATION_JSON) // json 데이터를 전달할 때 사용
                    .content(jsonRequest)) // json 테스트 데이터
                    .andExpect(status().is4xxClientError()); // 상태값이 400 error면 ok
  }


  @Test
  void 책_수정_컨트롤러_테스트 () throws Exception {
    String jsonRequest = """
         {
          "category": "테스트",
          "title": "테스트 제목 테스트 제목",
          "author": "테스트 저자 테스트 저자",
          "description": "테스트 내용 테스트 내용",
          "price": 20000,
          "discount": 40,
          "pages": 3200,
          "stock": 50
        }
        """;
    mockMvc.perform(post("/book/update/2")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonRequest))
            .andExpect(status().isOk());
  }
}
