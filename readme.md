## feature/select 주요 기능 
    - 특정 도서를 사용자가 원하는 키워드로 조회하는 기능이다
    - 조회는 사용자가 특정 키워드를 전달한다
    - 조회 조건에 맞는 결과가 없을 시 공백이 전달된다 
    - 조회 조건에 맞는 결과가 있을 시 List 로 반환된다
    
## 테스트 
    # 도서 번호로 조회
    [GET] localhost:8080/book/get/{bookId}
    Response : JSON
    
    # 도서 특정 키워드로 조회 
    [POST] localhost:8080/book/get
    Request Body {
        // 키워드는 무조건 1개 이상이 있어야 조회 가능합니다
        // 이 중 선택하여 json으로 post 요청하시면 됩니다. 
        // 2개 이상부터는 and 연산이 되어 모두 해당하는 것들만 조회됩니다

        String keyword,
        String category,
        Integer price,
        SellStatus status, // 판매 상태는 IN_STOCK / SOLD_OUT 
        String author
    }

## 검색 제약조건
    keyword : 1자 이상 ~  100자 이하 
    price : 1000원 이상
    author : 1자 이상 ~ 100자 이하
    status : SOLD_OUT / IN_STOCK

## JUnit 테스트 
    given 
        - 검색 키워드를 json으로 세팅한다 
        ex) 
            String jsonRequest = """
            {
              "category": "소설",
              "author" : "노수한",
              "keyword" : "스프링"
               ...
            }
            """;
    when 
        - Post 요청을 보낸다
    then 
        - 돌아오는 JSON 데이터가 검색한 도서 정보와 일치하는지 확인하여 맞으면 성공

## 테스트 예상 결과 
    [성공] Status code 200 + JSON 데이터 : 정상적으로 조회 완료 
    [실패] Status code 500 : 도서 번호와 일치하는 도서가 없음
    [실페] Status code 400 : 검색 키워드의 유효성 검사 실패 
 