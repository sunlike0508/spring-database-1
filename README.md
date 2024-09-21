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

## JDBC 개발 - 등록

**커넥션 획득**

`getConnection()` : 이전에 만들어둔 `DBConnectionUtil` 를 통해서 데이터베이스 커넥션을 획득한다.

**save() - SQL 전달**

`sql` : 데이터베이스에 전달할 SQL을 정의한다. 여기서는 데이터를 등록해야 하므로 `insert sql` 을 준비했다.

`con.prepareStatement(sql)` : 데이터베이스에 전달할 SQL과 파라미터로 전달할 데이터들을 준비한다.

* `sql` : `insert into member(member_id, money) values(?, ?)"`
* `pstmt.setString(1, member.getMemberId())` : SQL의 첫번째 `?` 에 값을 지정한다. 문자이므로 `setString` 을 사용한다.
* `pstmt.setInt(2, member.getMoney())` : SQL의 두번째 `?` 에 값을 지정한다. `Int` 형 숫자이므로 `setInt` 를 지정한다.
* `pstmt.executeUpdate()` : `Statement` 를 통해 준비된 SQL을 커넥션을 통해 실제 데이터베이스에 전달한다. 참고로 `executeUpdate()` 은 `int` 를 반환하는데
  영향받은 DB row 수를 반환한다. 여기서는 하나의 row 를 등록했으므로 1을 반환한다.

**executeUpdate()**

```java
int executeUpdate() throws SQLException; 
```

**리소스 정리**

쿼리를 실행하고 나면 리소스를 정리해야 한다.

여기서는 `Connection` , `PreparedStatement` 를 사용했다.

리소스를 정리할 때는 항상 역순으로 해야한다.

`Connection` 을 먼저 획득하고 `Connection` 을 통해 `PreparedStatement` 를 만들었기 때문에 리소스를 반환할 때는 `PreparedStatement` 를 먼저 종료하고, 그 다음에
`Connection` 을 종료하면 된다.

참고로 여기서 사용하지 않은 `ResultSet` 은 결과를 조회할 때 사용한다. 조금 뒤에 조회 부분에서 알아보자.

**주의**
리소스 정리는 꼭! 해주어야 한다.

따라서 예외가 발생하든, 하지 않든 항상 수행되어야 하므로 `finally` 구문에 주의해서 작성해야한다.

만약 이 부분을 놓치게 되면 커넥션이 끊어지지 않고 계속 유지되는 문제가 발생할 수 있다.

이런 것을 리소스 누수라고 하는데, 결과적으로 커넥션 부족으로 장애가 발생할 수 있다.

**참고**
`PreparedStatement` 는 `Statement` 의 자식 타입인데, `?` 를 통한 파라미터 바인딩을 가능하게 해준다.

참고로 SQL Injection 공격을 예방하려면 `PreparedStatement` 를 통한 파라미터 바인딩 방식을 사용해야 한다.

**ResultSet**
`ResultSet` 은 다음과 같이 생긴 데이터 구조이다. 보통 select 쿼리의 결과가 순서대로 들어간다.

예를 들어서 `select member_id, money` 라고 지정하면 `member_id` , `money` 라는 이름으로 데이터가 저장된다.

참고로 `select *` 을 사용하면 테이블의 모든 컬럼을 다 지정한다.

`ResultSet` 내부에 있는 커서( `cursor` )를 이동해서 다음 데이터를 조회할 수 있다.

`rs.next()` : 이것을 호출하면 커서가 다음으로 이동한다. 참고로 최초의 커서는 데이터를 가리키고 있지 않기 때문에 `rs.next()` 를 최초 한번은 호출해야 데이터를 조회할 수 있다.

`rs.next()` 의 결과가 `true` 면 커서의 이동 결과 데이터가 있다는 뜻이다.

`rs.next()` 의 결과가 `false` 면 더이상 커서가 가리키는 데이터가 없다는 뜻이다.

`rs.getString("member_id")` : 현재 커서가 가리키고 있는 위치의 `member_id` 데이터를 `String` 타입 으로 반환한다.

`rs.getInt("money")` : 현재 커서가 가리키고 있는 위치의 `money` 데이터를 `int` 타입으로 반환한다.

<img width="712" alt="Screenshot 2024-09-19 at 22 59 31" src="https://github.com/user-attachments/assets/2038f6a1-a388-4f45-ae0e-97314d9cfdb0">

# 커넥션풀과 데이터소스 이해

## 커넥션풀

<img width="916" alt="Screenshot 2024-09-20 at 22 33 56" src="https://github.com/user-attachments/assets/a2e2b58e-9a48-4261-8eb3-218cd03feea4">

데이터베이스 커넥션을 획득할 때는 다음과 같은 복잡한 과정을 거친다.

1. 애플리케이션 로직은 DB 드라이버를 통해 커넥션을 조회한다.
2. DB 드라이버는 DB와 `TCP/IP` 커넥션을 연결한다. 물론 이 과정에서 3 way handshake 같은 `TCP/IP` 연결
   을 위한 네트워크 동작이 발생한다.
3. DB 드라이버는 `TCP/IP` 커넥션이 연결되면 ID, PW와 기타 부가정보를 DB에 전달한다.
4. DB는 ID, PW를 통해 내부 인증을 완료하고, 내부에 DB 세션을 생성한다.
5. DB는 커넥션 생성이 완료되었다는 응답을 보낸다.
6. DB 드라이버는 커넥션 객체를 생성해서 클라이언트에 반환한다.

이렇게 커넥션을 새로 만드는 것은 과정도 복잡하고 시간도 많이 많이 소모되는 일이다.

DB는 물론이고 애플리케이션 서버에서도 `TCP/IP` 커넥션을 새로 생성하기 위한 리소스를 매번 사용해야 한다.

진짜 문제는 고객이 애플리케이션을 사용할 때, SQL을 실행하는 시간 뿐만 아니라 커넥션을 새로 만드는 시간이 추가 되기 때문에 결과적으로 응답속도에 영향을 준다.

이것은 사용자에게 좋지 않은 경험을 줄 수 있다.

**참고**

데이터베이스마다 커넥션을 생성하는 시간은 다르다.

시스템 상황마다 다르지만 MySQL 계열은 수 ms(밀 리초) 정도로 매우 빨리 커넥션을 확보할 수 있다.

반면에 수십 밀리초 이상 걸리는 데이터베이스들도 있다.

이런 문제를 한번에 해결하는 아이디어가 바로 커넥션을 미리 생성해두고 사용하는 커넥션 풀이라는 방법이다.

커넥션 풀은 이름 그대로 커넥션을 관리하는 풀(수영장 풀을 상상하면 된다.)이다.

<img width="924" alt="Screenshot 2024-09-20 at 22 34 05" src="https://github.com/user-attachments/assets/8a98474c-adec-4fff-8030-fa975b18afdd">

### 커넥션 풀 초기화

애플리케이션을 시작하는 시점에 커넥션 풀은 필요한 만큼 커넥션을 미리 확보해서 풀에 보관한다.

보통 얼마나 보관할 지는 서비스의 특징과 서버 스펙에 따라 다르지만 기본값은 보통 10개이다.

### 커넥션 풀의 연결 상태

커넥션 풀에 들어 있는 커넥션은 TCP/IP로 DB와 커넥션이 연결되어 있는 상태이기 때문에 언제든지 즉시 SQL을 DB에 전달할 수 있다.

### 커넥션 풀 사용1

애플리케이션 로직에서 이제는 DB 드라이버를 통해서 새로운 커넥션을 획득하는 것이 아니다.

이제는 커넥션 풀을 통해 이미 생성되어 있는 커넥션을 객체 참조로 그냥 가져다 쓰기만 하면 된다.

커넥션 풀에 커넥션을 요청하면 커넥션 풀은 자신이 가지고 있는 커넥션 중에 하나를 반환한다.

### 커넥션 풀 사용2

애플리케이션 로직은 커넥션 풀에서 받은 커넥션을 사용해서 SQL을 데이터베이스에 전달하고 그 결과를 받아서 처리한다.

커넥션을 모두 사용하고 나면 이제는 커넥션을 종료하는 것이 아니라, 다음에 다시 사용할 수 있도록 해당 커넥션을 그대로 커넥션 풀에 반환하면 된다.

여기서 주의할 점은 커넥션을 종료하는 것이 아니라 커넥션이 살아있는 상태로 커넥션 풀에 반환해야 한다는 것이다.

**정리**

애플리케이션 로직은 커넥션 풀에서 받은 커넥션을 사용해서 SQL을 데이터베이스에 전달하고 그 결과를 받아서 처리한다.

커넥션을 모두 사용하고 나면 이제는 커넥션을 종료하는 것이 아니라, 다음에 다시 사용할 수 있도록 해당 커넥션을 그대로 커넥션 풀에 반환하면 된다.

여기서 주의할 점은 커넥션을 종료하는 것이 아니라 커넥션이 살아있는 상태로 커넥션 풀에 반환해야 한다는 것이다.

적절한 커넥션 풀 숫자는 서비스의 특징과 애플리케이션 서버 스펙, DB 서버 스펙에 따라 다르기 때문에 성능 테스트를 통해서 정해야 한다.

커넥션 풀은 서버당 최대 커넥션 수를 제한할 수 있다.

따라서 DB에 무한정 연결이 생성되는 것을 막아주어서 DB를 보호하는 효과도 있다.

이런 커넥션 풀은 얻는 이점이 매우 크기 때문에 **실무에서는 항상 기본으로 사용**한다.

커넥션 풀은 개념적으로 단순해서 직접 구현할 수도 있지만, 사용도 편리하고 성능도 뛰어난 오픈소스 커넥션 풀이 많기 때문에 오픈소스를 사용하는 것이 좋다.

대표적인 커넥션 풀 오픈소스는 `commons-dbcp2` , `tomcat-jdbc pool` , `HikariCP` 등이 있다.

성능과 사용의 편리함 측면에서 최근에는 `hikariCP` 를 주로 사용한다.

스프링 부트 2.0 부터는 기본 커넥션 풀로 `hikariCP` 를 제공한다.

성능, 사용의 편리함, 안전성 측면에서 이미 검증이 되었기 때문에 커넥션 풀을 사용 할 때는 고민할 것 없이 `hikariCP` 를 사용하면 된다.

실무에서도 레거시 프로젝트가 아닌 이상 대부분 `hikariCP` 를 사용한다.

## DataSource 이해

### 커넥션을 획득하는 다양한 방법

커넥션을 얻는 방법은 앞서 학습한 JDBC `DriverManager` 를 직접 사용하거나, 커넥션 풀을 사용하는 등 다양한 방법이 존재한다.

예를 들어서 애플리케이션 로직에서 `DriverManager` 를 사용해서 커넥션을 획득하다가 `HikariCP` 같은 커넥션 풀을 사용하도록 변경하면 커넥션을 획득하는 애플리케이션 코드도 함께 변경해야 한다.

의존관계가 `DriverManager` 에서 `HikariCP` 로 변경되기 때문이다. 물론 둘의 사용법도 조금씩 다를 것이다.

### 커넥션을 획득하는 방법을 추상화

<img width="928" alt="Screenshot 2024-09-20 at 22 39 58" src="https://github.com/user-attachments/assets/7854d47c-aaff-4824-8f83-6576db1bef3d">

<img width="916" alt="Screenshot 2024-09-20 at 22 40 06" src="https://github.com/user-attachments/assets/01322a58-ca42-40ed-a837-f0a1b21ed645">

<img width="923" alt="Screenshot 2024-09-20 at 22 41 34" src="https://github.com/user-attachments/assets/550a9790-4a5b-4ad3-949e-a2e3c19acdad">

자바에서는 이런 문제를 해결하기 위해 `javax.sql.DataSource` 라는 인터페이스를 제공한다.

`DataSource` 는 **커넥션을 획득하는 방법을 추상화** 하는 인터페이스이다.

이 인터페이스의 핵심 기능은 커넥션 조회 하나이다. (다른 일부 기능도 있지만 크게 중요하지 않다.)

**DataSource 핵심 기능만 축약**

```java
public interface DataSource {

    Connection getConnection() throws SQLException;
}

```

**정리**

대부분의 커넥션 풀은 `DataSource` 인터페이스를 이미 구현해두었다.

따라서 개발자는 `DBCP2 커넥션 풀` , `HikariCP 커넥션 풀` 의 코드를 직접 의존하는 것이 아니라 `DataSource` 인터페이스에만 의존하도록 애플리케이션 로직을 작성하면 된다.

커넥션 풀 구현 기술을 변경하고 싶으면 해당 구현체로 갈아끼우기만 하면 된다.

`DriverManager` 는 `DataSource` 인터페이스를 사용하지 않는다.

따라서 `DriverManager` 는 직접 사용해야 한다.

따라서 `DriverManager` 를 사용하다가 `DataSource` 기반의 커넥션 풀을 사용하도록 변경하면 관련 코드를 다 고쳐야 한다.

이런 문제를 해결하기 위해 스프링은 `DriverManager` 도 `DataSource` 를 통해서 사 용할 수 있도록 `DriverManagerDataSource` 라는 `DataSource`를 구현한 클래스를
제공한다.

자바는 `DataSource` 를 통해 커넥션을 획득하는 방법을 추상화했다.

이제 애플리케이션 로직은 `DataSource` 인터페이스에만 의존하면 된다.

덕분에 `DriverManagerDataSource` 를 통해서 `DriverManager` 를 사용하다가 커넥션 풀을 사용하도록 코드를 변경해도 애플리케이션 로직은 변경하지 않아도 된다.

### DriverManager 와 DriverManagerDataSource 차이

**파라미터 차이**

기존 `DriverManager` 를 통해서 커넥션을 획득하는 방법과 `DataSource` 를 통해서 커넥션을 획득하는 방법에는 큰 차이가 있다.

```java
void dataSourceDriverManager() throws SQLException {
    Connection con1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    Connection con2 = DriverManager.getConnection(URL, USERNAME, PASSWORD);

    DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
    Connection con1 = dataSource.getConnection();
    Connection con2 = dataSource.getConnection();
}

```

`DriverManager` 는 커넥션을 획득할 때 마다 `URL` , `USERNAME` , `PASSWORD` 같은 파라미터를 계속 전달해야 한다.

반면에 `DataSource` 를 사용하는 방식은 처음 객체를 생성할 때만 필요한 파리미터를 넘겨두고, 커넥션을 획득할 때는 단순히 `dataSource.getConnection()` 만 호출하면 된다.

**설정과 사용의 분리**

**설정**: `DataSource` 를 만들고 필요한 속성들을 사용해서 `URL` , `USERNAME` , `PASSWORD` 같은 부분을 입력하는 것을 말한다. 이렇게 설정과 관련된 속성들은 한 곳에 있는 것이
향후 변경에 더 유연하게 대처할 수 있다.

**사용**: 설정은 신경쓰지 않고, `DataSource` 의 `getConnection()` 만 호출해서 사용하면 된다.

**설정과 사용의 분리 설명**

이 부분이 작아보이지만 큰 차이를 만들어내는데, 필요한 데이터를 `DataSource` 가 만들어지는 시점에 미리 다 넣어두게 되면, `DataSource` 를 사용하는 곳에서는
`dataSource.getConnection()` 만 호출하면 되므로, `URL` , `USERNAME` , `PASSWORD` 같은 속성들에 의존하지 않아도 된다.

그냥 `DataSource` 만 주입받아서 `getConnection()` 만 호출하면 된다.

쉽게 이야기해서 리포지토리(Repository)는 `DataSource` 만 의존하고, 이런 속성을 몰라도 된다.

애플리케이션을 개발해보면 보통 설정은 한 곳에서 하지만, 사용은 수 많은 곳에서 하게 된다.

덕분에 객체를 설정하는 부분과, 사용하는 부분을 좀 더 명확하게 분리할 수 있다.

### 커넥션 풀 연결

```java

@Test
void dataSourceConnectionPool() throws SQLException, InterruptedException {
    //커넥션 풀링: HikariProxyConnection(Proxy) -> JdbcConnection(Target) 
    HikariDataSource dataSource = new HikariDataSource();
    dataSource.setJdbcUrl(URL);
    dataSource.setUsername(USERNAME);
    dataSource.setPassword(PASSWORD);
    dataSource.setMaximumPoolSize(10);
    dataSource.setPoolName("MyPool");

    Connection con1 = dataSource.getConnection();
    Connection con2 = dataSource.getConnection();
}
```

**HikariConfig**

`HikariCP` 관련 설정을 확인할 수 있다. 풀의 이름( `MyPool` )과 최대 풀 수( `10` )을 확인할 수 있다.

**MyPool connection adder**
별도의 쓰레드 사용해서 커넥션 풀에 커넥션을 채우고 있는 것을 확인할 수 있다.

이 쓰레드는 커넥션 풀에 커넥션을 최 대 풀 수( `10` )까지 채운다.

그렇다면 왜 별도의 쓰레드를 사용해서 커넥션 풀에 커넥션을 채우는 것일까?

커넥션 풀에 커넥션을 채우는 것은 상대적으로 오래 걸리는 일이다.

애플리케이션을 실행할 때 커넥션 풀을 채울 때 까지 마냥 대기하고 있다면 애플리케이션 실행 시간이 늦어진다.

따라서 이렇게 별도의 쓰레드를 사용해서 커넥션 풀을 채워야 애플리케이션 실행 시간에 영향을 주지 않는다.

# 트랜잭션

데이터베이스에서 하나의 거래를 안전하게 처리하도록 보장해주는 것을 뜻한다.

계좌 이체를 생각해보면 된다.

## ACID

* 원자성 : 트랜잭선 내에서 실행한 작업들은 마치 하나의 작업인 것처럼 성공하거나 실패해야 한다.
* 일관성 : 모든 트랜잭션은 일관성 있는 데이터베이스 상태를 유지해야 한다. 무결성 제약 조건
* 격리성 : 동시에 실행되는 트랜잭션들이 서로에게 영향을 미치지 않도록 격리한다. 예를 들어 동시에 같은 데이터를 수정하지 못하도록 해야 한다.
    * 격리성은 동시성과 관련된 성능 이슈로 인해 트랜잭션 격리 수준을 선택할 수 있다.
* 지속성 : 트랜잭션이 끝아면 그 결과가 항상 기록되어야 한다. 시스템에 문제가 발생해도 데이터베이스 로그등을 사용해 트랜잭션 내용을 복구해야 한다.

트랜잭션은 일관성, 일관성, 지속성을 보장한다. 문제는 격리성.

격리성을 완벽히 보장하려면 거의 순서대로 실행해야 한다. 그러나 이렇게 하면 동시 처리 성능이 매우 나빠진다.

이로 인해 ANSI 표준은 트랜잭션의 격리 수준을 4단계로 나누어 정의한다.

## 격리수준 (Isolation Level)

* READ UNCOMMITED (커밋되지 않은 읽기)
* READ COMMITTED (커밋된 읽기)
* REPEATABLE READ(반복 가능한 읽기)
* SERIALIZABLE (직렬화 가능)

아래로 내려갈수록 격리성을 보장하나 성능은 느려진다.

## 데이터베이스 연결 구조와 DB 세션

<img width="699" alt="Screenshot 2024-09-21 at 21 59 12" src="https://github.com/user-attachments/assets/68af8446-2fa9-4c6e-a6a8-1c3c62fa7552">


사용자는 웹 애플리케이션 서버(WAS)나 DB 접근 툴 같은 클라이언트를 사용해서 데이터베이스 서버에 접근할 수 있다.

클라이언트는 데이터베이스 서버에 연결을 요청하고 커넥션을 맺게 된다.

이때 데이터베이스 서버는 내 부에 세션이라는 것을 만든다.

그리고 앞으로 해당 커넥션을 통한 모든 요청은 이 세션을 통해서 실행하게 된다.

쉽게 이야기해서 개발자가 클라이언트를 통해 SQL을 전달하면 현재 커넥션에 연결된 세션이 SQL을 실행한다.

세션은 트랜잭션을 시작하고, 커밋 또는 롤백을 통해 트랜잭션을 종료한다.

그리고 이후에 새로운 트랜잭션을 다 시 시작할 수 있다.

사용자가 커넥션을 닫거나, 또는 DBA(DB 관리자)가 세션을 강제로 종료하면 세션은 종료된다.

커넥션 풀이 10개의 커넥션을 생성하면, 세션도 10개 만들어진다.

## 락 (lock)

세션 1이 트랜잭션을 시작하고 데이터를 수정하는 동안 아직 커밋을 수행하지 않았는데, 다른 세션이 동시에 같은 데이터를 수정하게 되면 문제가 생긴다.

바로 원자성이 깨진다. 여기에 더해서 세션 1이 중간에 롤백하면 세션2는 잘못된 데이터를 수정하는 문제가 발생한다.

### DB 락 - 조회

**일반적인 조회는 락을 사용하지 않는다**

데이터베이스마다 다르지만, 보통 데이터를 조회할 때는 락을 획득하지 않고 바로 데이터를 조회할 수 있다.

예를 들어서 세션1이 락을 획득하고 데이터를 변경하고 있어도, 세션2에서 데이터를 조회는 할 수 있다.

물론 세션2에서 조회가 아니라 데이터를 변경하려면 락이 필요하기 때문에 락이 돌아올 때 까지 대기해야 한다.

**조회와 락**

데이터를 조회할 때도 락을 획득하고 싶을 때가 있다.

이럴 때는 `select for update` 구문을 사용하면 된다.

이렇게 하면 세션1이 조회 시점에 락을 가져가버리기 때문에 다른 세션에서 해당 데이터를 변경할 수 없다.

물론 이 경우도 트랜잭션을 커밋하면 락을 반납한다.

**조회 시점에 락이 필요한 경우는 언제일까?**

트랜잭션 종료 시점까지 해당 데이터를 다른 곳에서 변경하지 못하도록 강제로 막아야 할 때 사용한다.

예를 들어서 애플리케이션 로직에서 `memberA` 의 금액을 조회한 다음에 이 금액 정보로 애플리케이션에서 어떤 계산을 수행한다.

그런데 이 계산이 돈과 관련된 매우 중요한 계산이어서 계산을 완료할 때 까지 `memberA` 의 금액을 다른곳에서 변경하면 안된다.

이럴 때 조회 시점에 락을 획득하면 된다.

**정리**

트랜잭션과 락은 데이터베이스마다 실제 동작하는 방식이 조금씩 다르기 때문에, 해당 데이터베이스 메뉴얼을 확 인해보고, 의도한대로 동작하는지 테스트한 이후에 사용하자.

**애플리케이션에서 트랜잭션을 어떤 계층에 걸어야 할까? 쉽게 이야기해서 트랜잭션을 어디에서 시작하고, 어디에서 커밋해야할까?**

**비즈니스 로직과 트랜잭션**

<img width="704" alt="Screenshot 2024-09-21 at 23 14 41" src="https://github.com/user-attachments/assets/eed2cd11-c501-4338-bc34-cc6c2332fdf4">

트랜잭션은 비즈니스 로직이 있는 서비스 계층에서 시작해야 한다.

비즈니스 로직이 잘못되면 해당 비즈니스 로직 으로 인해 문제가 되는 부분을 함께 롤백해야 하기 때문이다.

그런데 트랜잭션을 시작하려면 커넥션이 필요하다.

결국 서비스 계층에서 커넥션을 만들고, 트랜잭션 커밋 이후에 커넥션을 종료해야 한다.

애플리케이션에서 DB 트랜잭션을 사용하려면 **트랜잭션을 사용하는 동안 같은 커넥션을 유지**해야한다.

그래야 같은 세션을 사용할 수 있다.

**커넥션과 세션**

<img width="692" alt="Screenshot 2024-09-21 at 23 14 45" src="https://github.com/user-attachments/assets/c80c7a3a-db42-4578-9d5e-21ce2a751dc0">

애플리케이션에서 같은 커넥션을 유지하려면 어떻게 해야할까? 가장 단순한 방법은 커넥션을 파라미터로 전달해서 같은 커넥션이 사용되도록 유지하는 것이다.






