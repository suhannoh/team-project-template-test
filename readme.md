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
        pages: 320,
        stock: 10
    }
##  JS HTTP 요청 테스트 코드 
    예시 
    fetch("http://localhost:8080/book/add", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
                category: "소설",
                title: "스프링 부트 완벽 가이드",
                author: "노수한",
                description: "스프링 부트와 JPA를 활용한 책 관리 프로젝트 예제입니다.",
                price: 15000,
                discount: 20,
                pages: 320,
                stock: 10
            })
        })
        .then(res => console.log("status:", res.status))
        .catch(err => console.error(err));
## JUnit 테스트 
    mockMvc ? 스프링 MVC 컨트롤러에 가짜 HTTP 요청을 날려볼 수 있는 테스트 도구
    실제 서버(Tomcat)를 띄우지 않고도 흐름 테스트가 가능함
    - @AutoconfigureMockMvc + @AutoWired MockMvc 
    @AutoconfigureMockMvc 를 붙여야 @Autowired MockMvc mockMvc 가능하다

    given - Json 형태 임의 데이터를 생성한다
        - 이때 """ 를 사용하면 추가 줄바꿈 (\n) 없이 줄바꿈이 가능하여 쉽게 사용가능하다
    when - Post 요청 날리고 Controller -> Service -> Repo 흐름 문제없이 타서 저장까지 하면 성공
    then - Controller response StatusCode가 
        - 200 이라면 성공
        - 500 이라면 실패 (이미 저장되어있는 책)
        - 400 이라면 입력값 유효성 검사 실패 

## 테스트 예상 결과 
    Status code 200 : 정상적으로 추가 완료 
    Status code 500 : 이미 존재하는 책으로 확인되어 저장하지 않음

## 사용한 주 어노테이션 
    @controller , @service @Components 등등
        - 이런 어노테이션은 모두 단 하나의 객체를 (싱글톤) 만들고 스프링 컨테이너 안에서 관리해준다. 
        - 매번 객체를 생성하지않고 빈을 재사용하는 이유는 성능과 일관성 때문임

    -Controller 
        - @RestController - Json 응답 컨트롤러
        - @RequiredArgsConstructor 
            - final이나 @NonNull 필드만 골라서 자동으로 생성자를 만들어주는 Lombok 기능.
            - 스프링에서 권장하는 DI 방식 (생성자주입) 을 쉽게 쓸 수 있는 어노테이션이다
        - @Valid
            - 컨트롤러에서 들어오는 요청 데이터(DTO)의 필드 값이 지정한 검증 조건에 맞는지 자동으로 검사해줌
        - @RequestBody
            - HTTP 요청 바디(body)에 담긴 JSON 데이터를 지정한 DTO 객체로 자동 변환해줌
            - JSON 데이터를 DTO 형태로 매핑할 때 사용함
    
    - Valid 관련
        - @Size 문자열, 배열, 컬렉션의 길이를 제한, (min, max) 를 지정함
        - @Notnull null 값만 허용하지 않음 , 빈 문자열 공백은 가능
        - @NotBlank null + 빈 문자열("") + 공백(" ") 까지 전부 허용하지 않음. 
        - @NotEmpty null + 빈 문자열("")은 불가,   
        - @ 어노테이션에 Message 작성 시 @Valid에서 유효성 검사 실패 시 이 메시지가 예외로 전달됨.

    - GlobalException 
        - @RestControllerAdvice
            - 전역에서 발생하는 예외를 처리하기 위해 어노테이션을 붙여야함
        - @ExceoptionHandler(~~.class) 
            - 특정 예외가 발생했을 때 어떤 방식으로 응답을 줄지 정의하는 메서드에 붙임 

    - AOP 코드를 건드리지 않고도 앞/뒤/전체 구간에 원하는 로직을 끼워넣기 위해 사용
        - @Aspect 
            - AOP를 사용하기 위해 선언
        - @Before
            - 메서드 시작 전에 실행되는 
        - @After    
            - 메서드가 끝나고 실행되는
        - @Around
            - 메서드 실행 전/후 모두 제어 가능 , 전체 흐름을 제어가능한 강력한 포인트 컷 