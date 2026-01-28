package com.yonsai.books;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
          "category": "",
          "title": "",
          "author": "",
          "description": "",
          "price": -20000,
          "discount": 101,
          "pages": 5
        }
        """;

    mockMvc.perform(post("/book/add")
                    .contentType(MediaType.APPLICATION_JSON) // json 데이터를 전달할 때 사용
                    .content(jsonRequest)) // json 테스트 데이터
                    .andExpect(status().is4xxClientError()); // 상태값이 400 error면 ok
  }

  /**
   * 도서 번호를 조회하여 데이터를 가져오는 테스트이다
   *
   * 중요
   *  - 이 테스트를 진행 전 책 추가 테스트를 이용하여 임의 데이터를 넣은 뒤 조회를 해야한다
   */
  @Test
  void 도서_번호_조회_컨트롤러_테스트 () throws Exception {
    long bookId = 1L;

    MvcResult result = mockMvc.perform(get("/book/1"))
            .andExpect(status().isOk())
            .andReturn();
    
    String json = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
    System.out.println("JSON 응답 : " + json);

  }
}
