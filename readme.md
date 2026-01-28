## feature/select 주요 기능 
    - 특정 도서를 조회하는 기능이다
    - 조회는 사용자가 특정 도서번호 (bookId) 를 전달한다
    - 없을 시 IllegalArgumentException 예외를 던진다
    
## 테스트 
    [GET] localhost:8080/book/{bookId}
    Response : JSON
## JUnit 테스트 
    given 
        - Long bookId 를 생성한다
    when 
        - GET 요청 날리고 에러없이 도서 정보를 반환 받는다
    then 
        - 돌아오는 JSON 데이터가 도서 정보와 일치하는지 확인하여 맞으면 성공

## 테스트 예상 결과 
    [성공] Status code 200 + JSON 데이터 : 정상적으로 조회 완료 
    [실패] Status code 500 : 도서 번호와 일치하는 도서가 없음
 