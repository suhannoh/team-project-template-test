## feature/update 주요 기능 
    - 책의 수정 정보를 전달받아 저장한다. 
    - 수정이 필요한 도서를 먼저 조회해야 한다 
    - 조회 후 도서의 ID를 통해 수정 접근이 가능하다
    
## 테스트 
    [POST] localhost:8080/book/update/{bookId}

    Request Body : {
         "category": "테스트",
          "title": "테스트 제목 테스트 제목",
          "author": "테스트 저자 테스트 저자",
          "description": "테스트 내용 테스트 내용",
          "price": 20000,
          "discount": 40,
          "pages": 3200,
          "stock": 50
    }

## 수정 제약조건 
    1. Body 안에는 도서의 입력 정보가 모두 있어야함
    2. 각 데이터에 맞는 제약조건은 도서 추가 조건과 동일함


## JUnit 테스트
    given 
        - Json 형태 임의 수정 데이터를 생성한다
    when 
        - Post 요청 
    then 
        - 데이터베이스에 도서가 수정되면 OK ! 

## 테스트 예상 결과 
    Status code 200 : 정상적으로 수정완료 
    Status code 400 : 수정 JSON(body) 입력값 오류 및 BookId 조회 실패
    Status code 500 : 실패 (서버 내부오류)
