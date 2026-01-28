## feature/add 주요 기능 
    - 책의 정보를 전달받아 저장한다. 
    - 이때 이미 동일한 책이 존재하는지 확인한다. 
    - 중복 확인은 제목 , 저자 , 판매상태를 기준으로 한다. 
    
## 테스트 
    [POST] localhost:8080/book/add 
    예시 
    Body : {
        category: "소설",
        title: "스프링 부트 완벽 가이드",
        author: "노수한",
        description: "스프링 부트와 JPA를 활용한 책 관리 프로젝트 예제입니다.",
        price: 15000,
        discount: 20,
        pages: 320
    }

## 테스트 예상 결과 
    Status code 200 : 정상적으로 추가 완료 
    Status code 500 : 이미 존재하는 책으로 확인되어 저장하지 않음

