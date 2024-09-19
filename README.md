# JDBC

Java DataBase Connectivity

옛날에는 db의 종류가 변경될때마다 어플리케이션 코드가 변경되어야 했다.

그래서 이를 하나로 추상화 시키기 위해 JDBC가 만들어졌다.

<img width="706" alt="Screenshot 2024-09-19 at 21 50 10" src="https://github.com/user-attachments/assets/7f58a6d3-25ef-431b-ad3c-5a175b140ae3">

대표적으로 다음 3가지 기능을 표준 인터페이스로 정의해서 제공한다.

`java.sql.Connection` - 연결

`java.sql.Statement` - SQL을 담은 내용

`java.sql.ResultSet` - SQL 요청 응답

자바는 이렇게 표준 인터페이스를 정의해두었다.

이제부터 개발자는 이 표준 인터페이스만 사용해서 개발하면 된다.

그런데 인터페이스만 있다고해서 기능이 동작하지는 않는다.

이 JDBC 인터페이스를 각각의 DB 벤더(회사)에서 자신 의 DB에 맞도록 구현해서 라이브러리로 제공하는데, 이것을 JDBC 드라이버라 한다.

예를 들어서 MySQL DB에 접근 할 수 있는 것은 MySQL JDBC 드라이버라 하고, Oracle DB에 접근할 수 있는 것은 Oracle JDBC 드라이버라 한다.

<img width="718" alt="Screenshot 2024-09-19 at 21 51 10" src="https://github.com/user-attachments/assets/4c54c62e-8ba5-40ee-9129-62025052d237">

**정리**

JDBC의 등장으로 다음 2가지 문제가 해결되었다.

1. 데이터베이스를 다른 종류의 데이터베이스로 변경하면 애플리케이션 서버의 데이터베이스 사용 코드도 함께 변경 해야하는 문제

    * 애플리케이션 로직은 이제 JDBC 표준 인터페이스에만 의존한다.
    * 데이터베이스를 다른 종류의 데이 터베이스로 변경하고 싶으면 JDBC 구현 라이브러리만 변경하면 된다.
    * 다른 종류의 데이터베이스로 변경해도 애플리케이션 서버의 사용 코드를 그대로 유지할 수 있다.

2. 개발자가 각각의 데이터베이스마다 커넥션 연결, SQL 전달, 그리고 그 결과를 응답 받는 방법을 새로 학습해야하 는 문제
    * 개발자는 JDBC 표준 인터페이스 사용법만 학습하면 된다. 한번 배워두면 수십개의 데이터베이스에 모두 동일하게 적용할 수 있다.

그러나

JDBC의 등장으로 많은 것이 편리해졌지만, 각각의 데이터베이스마다 SQL, 데이터타입 등의 일부 사용법 다르다.

ANSI SQL이라는 표준이 있기는 하지만 일반적인 부분만 공통화했기 때문에 한계가 있다.

대표적으로 실무에서 기본으로 사용하는 페이징 SQL은 각각의 데이터베이스마다 사용법이 다르다.

결국 데이터베이스를 변경하면 JDBC 코드는 변경하지 않아도 되지만 SQL은 해당 데이터베이스에 맞도록 변경 해야한다.

참고로 JPA(Java Persistence API)를 사용하면 이렇게 각각의 데이터베이스마다 다른 SQL을 정의해야 하는 문제도 많은 부분 해결할 수 있다.

## JDBC와 최신 데이터 접근 기술

최초 JDBC 직접 사용했다 그러나 그 이후 개선된 기술로 SQL Mapper와 ORM 기술을 사용한다.

### SQL Mapper

<img width="711" alt="Screenshot 2024-09-19 at 21 58 32" src="https://github.com/user-attachments/assets/c0a47cf3-775e-49af-91bb-df1fffe5983f">

대표 기술: 스프링 JdbcTemplate, MyBatis

**장점**

JDBC를 편리하게 사용하도록 도와준다.

SQL 응답 결과를 객체로 편리하게 변환해준다.

JDBC의 반복 코드를 제거해준다.

**단점**

개발자가 SQL을 직접 작성해야한다.

### ORM 기술

<img width="691" alt="Screenshot 2024-09-19 at 21 58 37" src="https://github.com/user-attachments/assets/e135737a-c45b-49ce-aaac-c9eb1aac41aa">

대표 기술: JPA, 하이버네이트, 이클립스링크

ORM은 객체를 관계형 데이터베이스 테이블과 매핑해주는 기술이다.

이 기술 덕분에 개발자는 반복적인 SQL을 직접 작성하지 않고, ORM 기술이 개발자 대신에 SQL을 동적으로 만들어 실행해준다.

추가로 각각 의 데이터베이스마다 다른 SQL을 사용하는 문제도 중간에서 해결해준다.

JPA는 자바 진영의 ORM 표준 인터페이스이고, 이것을 구현한 것으로 하이버네이트와 이클립스 링크 등의 구현 기술이 있다.

**SQL Mapper vs ORM 기술**

SQL Mapper와 ORM 기술 둘다 각각 장단점이 있다.

쉽게 설명하자면 SQL Mapper는 SQL만 직접 작성하면 나머지 번거로운 일은 SQL Mapper가 대신 해결해준다.

SQL Mapper는 SQL만 작성할 줄 알면 금방 배워서 사용할 수 있다.

ORM 기술은 SQL 자체를 작성하지 않아도 되어서 개발 생산성이 매우 높아진다.

편리한 반면에 쉬운 기술은 아니므로 실무에서 사용하려면 깊이있게 학습해야 한다.

강의 뒷 부분에서 다양한 데이터 접근 기술을 설명하는데, 그때 SQL Mapper인 JdbcTemplate과 MyBatis를 학습하고 코드로 활용해본다.

그리고 ORM의 대표 기술인 JPA도 학습하고 코드로 활용해본다.

이 과정을 통해서 각각의 기술들의 장단 점을 파악하고, 어떤 기술을 언제 사용해야 하는지 자연스럽게 이해하게 될 것이다.

**중요**
이런 기술들도 내부에서는 모두 JDBC를 사용한다.

따라서 JDBC를 직접 사용하지는 않더라도, JDBC가 어떻게 동작하는지 기본 원리를 알아두어야 한다.

그래야 해당 기술들을 더 깊이있게 이해할 수 있고, 무엇보다 문제가 발 생했을 때 근본적인 문제를 찾아서 해결할 수 있다

**JDBC는 자바 개발자라면 꼭 알아두어야 하는 필수 기본 기술** 이다.

## JDBC DriverManager 연결 이해

### JDBC 커넥션 인터페이스와 구현

<img width="702" alt="Screenshot 2024-09-19 at 22 16 44" src="https://github.com/user-attachments/assets/7a8c170a-02c9-4796-8180-c64d0a880d78">

JDBC는 `java.sql.Connection` 표준 커넥션 인터페이스를 정의한다.

H2 데이터베이스 드라이버는 JDBC Connection 인터페이스를 구현한 `org.h2.jdbc.JdbcConnection` 구현체를 제공한다.

### DriverManager 커넥션 요청 흐름

<img width="702" alt="Screenshot 2024-09-19 at 22 14 12" src="https://github.com/user-attachments/assets/4aae0a08-2d44-4b70-8c8c-c14edcdaab73">

JDBC가 제공하는 `DriverManager` 는 라이브러리에 등록된 DB 드라이버들을 관리하고, 커넥션을 획득하는 기능을 제공한다.

1. JDBC가 제공하는 `DriverManager` 는 라이브러리에 등록된 DB 드라이버들을 관리하고, 커넥션을 획득하는 기능을 제공한다.
2. `DriverManager` 는 라이브러리에 등록된 드라이버 목록을 자동으로 인식한다. 이 드라이버들에게 순서대로 다음 정보를 넘겨서 커넥션을 획득할 수 있는지 확인한다.
    * URL: 예) `jdbc:h2:tcp://localhost/~/test`
    * 이름, 비밀번호 등 접속에 필요한 추가 정보
    * 여기서 각각의 드라이버는 URL 정보를 체크해서 본인이 처리할 수 있는 요청인지 확인한다. 예를 들어서 URL이 `jdbc:h2` 로 시작하면 이것은 h2 데이터베이스에 접근하기 위한 규칙이다.
    * 따라서 H2 드라이버는 본인이 처리할 수 있으므로 실제 데이터베이스에 연결해서 커넥션을 획득하고 이 커넥션을 클라이언트에 반환한다.
    * 반면에 URL이 `jdbc:h2` 로 시작했는데 MySQL 드라이버가 먼저 실행되면 이 경우 본인이 처리 할 수 없다는 결과를 반환하게 되고, 다음 드라이버에게 순서가 넘어간다.
3. 이렇게 찾은 커넥션 구현체가 클라이언트에 반환된다.

우리는 H2 데이터베이스 드라이버만 라이브러리에 등록했기 때문에 H2 드라이버가 제공하는 H2 커넥션을 제공받는다.

물론 이 H2 커넥션은 JDBC가 제공하는 `java.sql.Connection` 인터페이스를 구현하고 있다.

**H2 데이터베이스 드라이버 라이브러리**

```groovy
runtimeOnly 'com.h2database:h2' //h2-x.x.xxx.jar
```

