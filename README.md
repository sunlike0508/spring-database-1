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

## 트랜잭션 - 문제 해결

지금까지 개발한건 3가지 문제점이 있다.

1) 트랜잭션 문제

가장 큰 문제는 트랜잭션을 적용하면서 생긴 다음과 같은 문제들이다.

* JDBC 구현 기술이 서비스 계층에 누수되는 문제
    * 트랜잭션을 적용하기 위해 JDBC 구현 기술이 서비스 계층에 누수되었다.
    * 서비스 계층은 순수해야 한다. 구현 기술을 변경해도 서비스 계층 코드는 최대한 유지할 수 있어야 한다. (변화에 대응)
        * 그래서 데이터 접근 계층에 JDBC 코드를 다 몰아두는 것이다.
        * 물론 데이터 접근 계층의 구현 기술이 변경될 수도 있으니 데이터 접근 계층은 인터페이스를 제공하는 것이 좋다.
    * 서비스 계층은 특정 기술에 종속되지 않아야 한다. 지금까지 그렇게 노력해서 데이터 접근 계층으로 JDBC 관련 코드를 모았는데, 트랜잭션을 적용하면서 결국 서비스 계층에 JDBC 구현 기술의 누수가
      발생했다.
* 트랜잭션 동기화 문제
    * 같은 트랜잭션을 유지하기 위해 커넥션을 파라미터로 넘겨야 한다.
    * 이때 파생되는 문제들도 있다. 똑같은 기능도 트랜잭션용 기능과 트랜잭션을 유지하지 않아도 되는 기능으 로 분리해야 한다.
* 트랜잭션 적용 반복 문제
    * 트랜잭션 적용 코드를 보면 반복이 많다. `try` , `catch` , `finally` ...

2) 예외 누수 문제

* 데이터 접근 계층의 JDBC 구현 기술 예외가 서비스 계층으로 전파된다.
* `SQLException` 은 체크 예외이기 때문에 데이터 접근 계층을 호출한 서비스 계층에서 해당 예외를 잡아서 처리 하거나 명시적으로 `throws` 를 통해서 다시 밖으로 던져야한다.
* `SQLException` 은 JDBC 전용 기술이다. 향후 JPA나 다른 데이터 접근 기술을 사용하면, 그에 맞는 다른 예외로 변경해야 하고, 결국 서비스 코드도 수정해야 한다.

3) JDBC 반복 문제

* 지금까지 작성한 `MemberRepository` 코드는 순수한 JDBC를 사용했다.
* 이 코드들은 유사한 코드의 반복이 너무 많다. `try` , `catch` , `finally` ...
* 커넥션을 열고, `PreparedStatement` 를 사용하고, 결과를 매핑하고... 실행하고, 커넥션과 리소스를 정리한다.

### 트랜잭션 추상화

JDBC 기술을 JPA로 변경하면 서비스 계층 코드를 모수 수정해야한다.

* JDBC

```java
public void accountTransfer(String fromId, String toId, int money) throws SQLException {
    Connection con = dataSource.getConnection();
    try {
        con.setAutoCommit(false); //트랜잭션 시작 //비즈니스 로직
        bizLogic(con, fromId, toId, money);
        con.commit(); //성공시 커밋
    } catch(Exception e) {
        con.rollback(); //실패시 롤백
        throw new IllegalStateException(e);
    } finally {
        release(con);
    }
}
```

* JPA

```java
public static void main(String[] args) {
    //엔티티 매니저 팩토리 생성
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
    EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성 
    EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

    try {
        tx.begin(); //트랜잭션 시작 logic(em); //비즈니스 로직 tx.commit();//트랜잭션 커밋
    } catch(Exception e) {
        tx.rollback(); //트랜잭션 롤백
    } finally {
        em.close(); //엔티티 매니저 종료
    }
    emf.close(); //엔티티 매니저 팩토리 종료 
}
```

트랜잭션을 사용하는 코드는 데이터 접근 기술마다 다르다.

만약 다음 그림과 같이 JDBC 기술을 사용하고, JDBC 트랜 잭션에 의존하다가 JPA 기술로 변경하게 되면 서비스 계층의 트랜잭션을 처리하는 코드도 모두 함께 변경해야 한다.

이렇게 JDBC 기술을 사용하다가 JPA 기술로 변경하게 되면 서비스 계층의 코드도 JPA 기술을 사용하도록 함께 수정해야 한다.

**트랜잭션 추상화**

<img width="932" alt="Screenshot 2024-09-23 at 22 20 56" src="https://github.com/user-attachments/assets/405e6d1a-f0bb-4f33-a408-31fd77cb05f8">

이 문제를 해결하려면 트랜잭션 기능을 추상화하면 된다.

아주 단순하게 생각하면 다음과 같은 인터페이스를 만들어서 사용하면 된다.

```java

public interface TxManager {

    begin();

    commit();

    rollback();
}
```

트랜잭션은 사실 단순하다. 트랜잭션을 시작하고, 비즈니스 로직의 수행이 끝나면 커밋하거나 롤백하면 된다.

그리고 다음과 같이 `TxManager` 인터페이스를 기반으로 각각의 기술에 맞는 구현체를 만들면 된다.

* `JdbcTxManager` : JDBC 트랜잭션 기능을 제공하는 구현체
* `JpaTxManager` : JPA 트랜잭션 기능을 제공하는 구현체

서비스는 특정 트랜잭션 기술에 직접 의존하는 것이 아니라, `TxManager` 라는 추상화된 인터페이스에 의존한다.

이제 원하는 구현체를 DI를 통해서 주입하면 된다. 예를 들어서 JDBC 트랜잭션 기능이 필요하면 `JdbcTxManager` 를 서비스에 주입하고,

JPA 트랜잭션 기능으로 변경해야 하면 `JpaTxManager` 를 주입하면 된다.

클라이언트인 서비스는 인터페이스에 의존하고 DI를 사용한 덕분에 OCP 원칙을 지키게 되었다.

이제 트랜잭션 을 사용하는 서비스 코드를 전혀 변경하지 않고, 트랜잭션 기술을 마음껏 변경할 수 있다.

**스프링의 트랜잭션 추상화**

<img width="916" alt="Screenshot 2024-09-23 at 22 21 46" src="https://github.com/user-attachments/assets/0e20a815-4dd8-4ead-9800-91231dd208f9">

스프링은 이미 이런 고민을 다 해두었다.

우리는 스프링이 제공하는 트랜잭션 추상화 기술을 사용하면 된다.

심지어 데 이터 접근 기술에 따른 트랜잭션 구현체도 대부분 만들어두어서 가져다 사용하기만 하면 된다.

스프링 트랜잭션 추상화의 핵심은 `PlatformTransactionManager` 인터페이스이다.

`org.springframework.transaction.PlatformTransactionManager`

**참고**

스프링 5.3부터는 JDBC 트랜잭션을 관리할 때 `DataSourceTransactionManager` 를 상속받아서 약간의 기능을 확장한 JdbcTransactionManager` 를 제공한다.

둘의 기능 차이는 크지 않으므로 같은 것으로 이해하면 된다.

```java
 package org.springframework.transaction;

public interface PlatformTransactionManager extends TransactionManager {

    TransactionStatus getTransaction(@Nullable TransactionDefinition definition) throws TransactionException;

    void commit(TransactionStatus status) throws TransactionException;

    void rollback(TransactionStatus status) throws TransactionException;
}
```

`getTransaction()` : 트랜잭션을 시작한다.

이름이 `getTransaction()` 인 이유는 기존에 이미 진행중인 트랜잭션이 있는 경우 해당 트랜잭션에 참 여할 수 있기 때문이다.

참고로 트랜잭션 참여, 전파에 대한 부분은 뒤에서 설명한다. 지금은 단순히 트랜잭션을 시작하는 것으로 이해하면 된다.

`commit()` : 트랜잭션을 커밋한다.

`rollback()` : 트랜잭션을 롤백한다.

앞으로 `PlatformTransactionManager` 인터페이스와 구현체를 포함해서 **트랜잭션 매니저**로 줄여서 이야기하겠다.

### 트랜잭션 동기화

스프링이 제공하는 트랜잭션 매니저는 2가지 역할을 한다.

1. 트랜잭션 추상화 : 이거는 바로 위에 설명

2. 리소스 동기화

트랜잭션을 유지하려면 트랜잭션의 시작부터 끝까지 같은 데이터베이스 커넥션을 유지해아한다.

결국 같은 커넥션을 동기화(맞추어 사용)하기 위해서 이전에는 파라미터로 커넥션을 전달하는 방법을 사용했다.

파라미터로 커넥션을 전달하는 방법은 코드가 지저분해지는 것은 물론이고,

커넥션을 넘기는 메서드와 넘기지 않는 메서드를 중복해서 만들어야 하는 등 여러가지 단점들이 많다.

**커넥션과 세션**

<img width="925" alt="Screenshot 2024-09-23 at 22 30 04" src="https://github.com/user-attachments/assets/8713c4d4-05d6-4481-94c5-c6481d8edada">

**트랜잭션 매니저와 트랜잭션 동기화 매니저**

<img width="921" alt="Screenshot 2024-09-23 at 22 30 33" src="https://github.com/user-attachments/assets/88011485-2612-4328-ab91-6482232dc2de">

스프링은 **트랜잭션 동기화 매니저**를 제공한다.

이것은 쓰레드 로컬( `ThreadLocal` )을 사용해서 커넥션을 동기 화해준다.

트랜잭션 매니저는 내부에서 이 트랜잭션 동기화 매니저를 사용한다.

트랜잭션 동기화 매니저는 쓰레드 로컬을 사용하기 때문에 멀티쓰레드 상황에 안전하게 커넥션을 동기화 할 수 있다.

따라서 커넥션이 필요하면 트랜잭션 동기화 매니저를 통해 커넥션을 획득하면 된다. 따라서 이전처럼 파라미터로 커넥션을 전달하지 않아도 된다.

**동작 방식을 간단하게 설명하면 다음과 같다.**

1. 트랜잭션을 시작하려면 커넥션이 필요하다. 트랜잭션 매니저는 데이터소스를 통해 커넥션을 만들고 트랜잭션을 시작한다.
2. 트랜잭션 매니저는 트랜잭션이 시작된 커넥션을 트랜잭션 동기화 매니저에 보관한다.
3. 리포지토리는 트랜잭션 동기화 매니저에 보관된 커넥션을 꺼내서 사용한다. 따라서 파라미터로 커넥션을 전달하지 않아도 된다.
4. 트랜잭션이 종료되면 트랜잭션 매니저는 트랜잭션 동기화 매니저에 보관된 커넥션을 통해 트랜잭션을 종료하고, 커넥션도 닫는다.

**트랜잭션 동기화 매니저**
다음 트랜잭션 동기화 매니저 클래스를 열어보면 쓰레드 로컬을 사용하는 것을 확인할 수 있다.

`org.springframework.transaction.support.TransactionSynchronizationManager`

**참고**
쓰레드 로컬을 사용하면 각각의 쓰레드마다 별도의 저장소가 부여된다.

따라서 해당 쓰레드만 해당 데이터에 접근 할 수 있다.

### 트랜잭션 문제 해결 - 트랜잭션 매니저1

**DataSourceUtils.getConnection()**

`getConnection()` 에서 `DataSourceUtils.getConnection()` 를 사용하도록 변경된 부분을 특히 주의해야 한다.

`DataSourceUtils.getConnection()` 는 다음과 같이 동작한다.

**트랜잭션 동기화 매니저가 관리하는 커넥션이 있으면 해당 커넥션을 반환한다.**

트랜잭션 동기화 매니저가 관리하는 커넥션이 없는 경우 새로운 커넥션을 생성해서 반환한다.

**DataSourceUtils.releaseConnection()**

`close()` 에서 `DataSourceUtils.releaseConnection()` 를 사용하도록 변경된 부분을 특히 주의해야한다.

커넥션을 `con.close()` 를 사용해서 직접 닫아버리면 커넥션이 유지되지 않는 문제가 발생한다.

이 커넥션은 이후 로직은 물론이고, 트랜잭션을 종료(커밋, 롤백)할 때 까지 살아있어야 한다.

`DataSourceUtils.releaseConnection()` 을 사용하면 커넥션을 바로 닫는 것이 아니다.

* 트랜잭션을 사용하기 위해 동기화된 커넥션은 커넥션을 닫지 않고 그대로 유지해준다.
* 트랜잭션 동기화 매니저가 관리하는 커넥션이 없는 경우 해당 커넥션을 닫는다.

### 트랜잭션 문제 해결 - 트랜잭션 매니저2

**트랜잭션 매니저1 - 트랜잭션 시작**

<img width="926" alt="Screenshot 2024-09-23 at 22 56 03" src="https://github.com/user-attachments/assets/801547e0-c983-41c3-be95-b4b49380fe9a">

클라이언트의 요청으로 서비스 로직을 실행한다.

1. 서비스 계층에서 `transactionManager.getTransaction()` 을 호출해서 트랜잭션을 시작한다.
2. 트랜잭션을 시작하려면 먼저 데이터베이스 커넥션이 필요하다. 트랜잭션 매니저는 내부에서 데이터소스를 사용해서 커넥션을 생성한다.
3. 커넥션을 수동 커밋 모드로 변경해서 실제 데이터베이스 트랜잭션을 시작한다.
4. 커넥션을 트랜잭션 동기화 매니저에 보관한다.
5. 트랜잭션 동기화 매니저는 쓰레드 로컬에 커넥션을 보관한다. 따라서 멀티 쓰레드 환경에 안전하게 커넥션을 보관 할 수 있다.

**트랜잭션 매니저2 - 로직 실행**

<img width="915" alt="Screenshot 2024-09-23 at 22 56 36" src="https://github.com/user-attachments/assets/03451022-6cc3-4bf5-b83f-0bba71391b9d">

서비스는 비즈니스 로직을 실행하면서 리포지토리의 메서드들을 호출한다. 이때 커넥션을 파라미터로 전달하지 않는다.

7. 리포지토리 메서드들은 트랜잭션이 시작된 커넥션이 필요하다. 리포지토리는 `DataSourceUtils.getConnection()` 을 사용해서 트랜잭션 동기화 매니저에 보관된 커넥션을 꺼내서 사용한다. 이
   과정을 통해서 자연스럽게 같은 커넥션을 사용하고, 트랜잭션도 유지된다.
8. 획득한 커넥션을 사용해서 SQL을 데이터베이스에 전달해서 실행한다.

**트랜잭션 매니저3 - 트랜잭션 종료**

<img width="934" alt="Screenshot 2024-09-23 at 22 57 31" src="https://github.com/user-attachments/assets/785e964e-c6eb-479f-b650-8deebe850316">

9. 비즈니스 로직이 끝나고 트랜잭션을 종료한다. 트랜잭션은 커밋하거나 롤백하면 종료된다.
10. 트랜잭션을 종료하려면 동기화된 커넥션이 필요하다. 트랜잭션 동기화 매니저를 통해 동기화된 커넥션을 획득한다.
11. 획득한 커넥션을 통해 데이터베이스에 트랜잭션을 커밋하거나 롤백한다.
12. 전체 리소스를 정리한다.
    * 트랜잭션 동기화 매니저를 정리한다. 쓰레드 로컬은 사용후 꼭 정리해야 한다.
    * `con.setAutoCommit(true)` 로 되돌린다. 커넥션 풀을 고려해야 한다.
    * `con.close()` 를 호출해셔 커넥션을 종료한다. 커넥션 풀을 사용하는 경우 `con.close()` 를 호출하면 커넥션 풀에 반환된다.

**정리**
트랜잭션 추상화 덕분에 서비스 코드는 이제 JDBC 기술에 의존하지 않는다.

이후 JDBC에서 JPA로 변경해도 서비스 코드를 그대로 유지할 수 있다.

기술 변경시 의존관계 주입만 `DataSourceTransactionManager` 에서 `JpaTransactionManager` 로 변경해주면 된다.

`java.sql.SQLException` 이 아직 남아있지만 이 부분은 뒤에 예외 문제에서 해결하자.

트랜잭션 동기화 매니저 덕분에 커넥션을 파라미터로 넘기지 않아도 된다.

### 트랜잭션 문제해결3 - 템플릿

```java

void biz() {
    //트랜잭션 시작
    TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

    try {
        //비즈니스 로직
        bizLogic(fromId, toId, money);
        transactionManager.commit(status); //성공시 커밋 
    } catch(Exception e) {
        transactionManager.rollback(status); //실패시 롤백
        throw new IllegalStateException(e);
    }
}
```

* 트랜잭션을 시작하고, 비즈니스 로직을 실행하고, 성공하면 커밋하고, 예외가 발생해서 실패하면 롤백한다.
* 다른 서비스에서 트랜잭션을 시작하려면 `try` , `catch` , `finally` 를 포함한 성공시 커밋, 실패시 롤백 코드가 반복될 것이다.
* 이런 형태는 각각의 서비스에서 반복된다. 달라지는 부분은 비즈니스 로직 뿐이다.
* 이럴 때 템플릿 콜백 패턴을 활용하면 이런 반복 문제를 깔끔하게 해결할 수 있다.

**트랜잭션 템플릿**

템플릿 콜백 패턴을 적용하려면 템플릿을 제공하는 클래스를 작성해야 하는데, 스프링은 `TransactionTemplate` 라 는 템플릿 클래스를 제공한다.

**TransactionTemplate**

```java
public class TransactionTemplate {

    private PlatformTransactionManager transactionManager;


    public <T> T execute(TransactionCallback<T> action) {}


    void executeWithoutResult(Consumer<TransactionStatus> action) {}
}
```

* `execute()` : 응답 값이 있을 때 사용한다.
* `executeWithoutResult()` : 응답 값이 없을 때 사용한다.

**정리**

트랜잭션 템플릿 덕분에, 트랜잭션을 사용할 때 반복하는 코드를 제거할 수 있었다.

하지만 이곳은 서비스 로직인데 비즈니스 로직 뿐만 아니라 트랜잭션을 처리하는 기술 로직이 함께 포함되어 있다.

애플리케이션을 구성하는 로직을 핵심 기능과 부가 기능으로 구분하자면 서비스 입장에서 비즈니스 로직은 핵심 기능이고, 트랜잭션은 부가 기능이다.

이렇게 비즈니스 로직과 트랜잭션을 처리하는 기술 로직이 한 곳에 있으면 두 관심사를 하나의 클래스에서 처리하 게 된다.

결과적으로 코드를 유지보수하기 어려워진다.

서비스 로직은 가급적 핵심 비즈니스 로직만 있어야 한다.

하지만 트랜잭션 기술을 사용하려면 어쩔 수 없이 트랜잭션 코드가 나와야 한다.

어떻게 하면 이 문제를 해결할 수 있을까?

### 트랜잭션 문제 해결 - AOP

트랜잭션 템플릿 덕분에 트랜잭션을 처리하는 반복 코드는 해결할 수 있었다.

하지만 서비스 계층에 순수한 비즈 니스 로직만 남긴다는 목표는 아직 달성하지 못했다.

이럴 때 스프링 AOP를 통해 프록시를 도입하면 문제를 깔끔하게 해결할 수 있다.

**프록시 도입 전**

<img width="913" alt="Screenshot 2024-09-24 at 21 53 58" src="https://github.com/user-attachments/assets/bb0afb6c-98cb-4e58-90ff-148f453504fd">

**프록시 도입 후**

<img width="921" alt="Screenshot 2024-09-24 at 21 54 04" src="https://github.com/user-attachments/assets/e4ec3b4c-a0b6-4627-acb0-d82f64eec8c5">


**트랜잭션 프록시 코드 예시**

```java
 public class TransactionProxy {

    private MemberService target;


    public void logic() { //트랜잭션 시작
        TransactionStatus status = transactionManager.getTransaction();

        try {
            //실제 대상 호출
            target.logic();
            transactionManager.commit(status); //성공시 커밋
        } catch(Exception e) {
            transactionManager.rollback(status); //실패시 롤백 
            // throw new IllegalStateException(e);
        }
    }
}
```

* 프록시 도입 전: 서비스에 비즈니스 로직과 트랜잭션 처리 로직이 함께 섞여있다.

* 프록시 도입 후: 트랜잭션 프록시가 트랜잭션 처리 로직을 모두 가져간다. 그리고 트랜잭션을 시작한 후에 실제 서 비스를 대신 호출한다. 트랜잭션 프록시 덕분에 서비스 계층에는 순수한 비즈니즈 로직만 남길 수
  있다.

**스프링이 제공하는 트랜잭션 AOP**

스프링이 제공하는 AOP 기능을 사용하면 프록시를 매우 편리하게 적용할 수 있다.

물론 스프링 AOP를 직접 사용해서 트랜잭션을 처리해도 되지만, 트랜잭션은 매우 중요한 기능이고, 전세계 누구나 다 사용하는 기능이다.

스프링은 트랜잭션 AOP를 처리하기 위한 모든 기능을 제공한다.

스프링 부트를 사용하 면 트랜잭션 AOP를 처리하기 위해 필요한 스프링 빈들도 자동으로 등록해준다.

개발자는 트랜잭션 처리가 필요한 곳에 `@Transactional` 애노테이션만 붙여주면 된다.

스프링의 트랜잭션 AOP는 이 애노테이션을 인식해서 트랜잭션 프록시를 적용해준다.

**@Transactional**

`org.springframework.transaction.annotation.Transactional`

### 정리

**선언적 트랜잭션 관리 vs 프로그래밍 방식 트랜잭션 관리**

* 선언적 트랜잭션 관리(Declarative Transaction Management)

`@Transactional` 애노테이션 하나만 선언해서 매우 편리하게 트랜잭션을 적용하는 것을 선언적 트랜잭션 관리라 한다.

선언적 트랜잭션 관리는 과거 XML에 설정하기도 했다.

이름 그대로 해당 로직에 트랜잭션을 적용하겠다라고 어딘가에 선언하기만 하면 트랜잭션이 적용되는 방식이다.

* 프로그래밍 방식의 트랜잭션 관리(programmatic transaction management)

트랜잭션 매니저 또는 트랜잭션 템플릿 등을 사용해서 트랜잭션 관련 코드를 직접 작성하는 것을 프로그래밍 방식의 트랜잭션 관리라 한다.

선언적 트랜잭션 관리가 프로그래밍 방식에 비해서 훨씬 간편하고 실용적이기 때문에 실무에서는 대부분 선언적 트랜잭션 관리를 사용한다.

프로그래밍 방식의 트랜잭션 관리는 스프링 컨테이너나 스프링 AOP 기술 없이 간단히 사용할 수 있지만 실무에서는 대부분 스프링 컨테이너와 스프링 AOP를 사용하기 때문에 거의 사용되지 않는다.

프로그래밍 방식 트랜잭션 관리는 테스트 시에 가끔 사용될 때는 있다.

### 스프링 부트의 자동 리소스 등록

**데이터소스와 트랜잭션 매니저를 스프링 빈으로 직접 등록**

```java

@Bean
DataSource dataSource() {
    return new DriverManagerDataSource(URL, USERNAME, PASSWORD);
}


@Bean
PlatformTransactionManager transactionManager() {
    return new DataSourceTransactionManager(dataSource());
}

```

기존에는 이렇게 데이터소스와 트랜잭션 매니저를 직접 스프링 빈으로 등록해야 했다.

그런데 스프링 부트가 나오면서 많은 부분이 자동화되었다

**데이터소스 - 자동 등록**

스프링 부트는 데이터소스( `DataSource` )를 스프링 빈에 자동으로 등록한다.

자동으로 등록되는 스프링 빈 이름: `dataSource`

참고로 개발자가 직접 데이터소스를 빈으로 등록하면 스프링 부트는 데이터소스를 자동으로 등록하지 않는다.

이때 스프링 부트는 다음과 같이 `application.properties` 에 있는 속성을 사용해서 `DataSource` 를 생성한다.

그리고 스프링 빈에 등록한다.

`application.properties`

```properties
spring.datasource.url=jdbc:h2:tcp://localhost/~/test
spring.datasource.username=sa
spring.datasource.password= 
```

스프링 부트가 기본으로 생성하는 데이터소스는 커넥션풀을 제공하는 `HikariDataSource` 이다.

커넥션풀과 관련된 설정도 `application.properties` 를 통해서 지정할 수 있다.

`spring.datasource.url` 속성이 없으면 내장 데이터베이스(메모리 DB)를 생성하려고 시도한다.

**트랜잭션 매니저 - 자동 등록**

스프링 부트는 적절한 트랜잭션 매니저( `PlatformTransactionManager` )를 자동으로 스프링 빈에 등록한다.

자동으로 등록되는 스프링 빈 이름: `transactionManager`

참고로 개발자가 직접 트랜잭션 매니저를 빈으로 등록하면 스프링 부트는 트랜잭션 매니저를 자동으로 등록하지 않는다.

어떤 트랜잭션 매니저를 선택할지는 현재 등록된 라이브러리를 보고 판단하는데, JDBC를 기술을 사용하면 `DataSourceTransactionManager` 를 빈으로 등록하고, JPA를 사용하면
`JpaTransactionManager` 를 빈으로 등록한다.

둘다 사용하는 경우 `JpaTransactionManager` 를 등록한다.

참고로 `JpaTransactionManager` 는 `DataSourceTransactionManager` 가 제공하는 기능도 대부분 지원한다.

**데이터소스, 트랜잭션 매니저 직접 등록**

```java

@TestConfiguration
static class TestConfig {

    @Bean
    DataSource dataSource() {
        return new DriverManagerDataSource(URL, USERNAME, PASSWORD);
    }


    @Bean
    PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }


    @Bean
    MemberRepositoryV3 memberRepositoryV3() {
        return new MemberRepositoryV3(dataSource());
    }


    @Bean
    MemberServiceV3_3 memberServiceV3_3() {
        return new MemberServiceV3_3(memberRepositoryV3());
    }
}
```

이렇게 데이터소스와 트랜잭션 매니저를 직접 등록하면 스프링 부트는 데이터소스와 트 랜잭션 매니저를 자동으로 등록하지 않는다.

## 예외

<img width="938" alt="Screenshot 2024-09-25 at 22 31 36" src="https://github.com/user-attachments/assets/0d34c5aa-83af-4d48-a0e8-e4f3310ce791">

* `Object` : 예외도 객체이다. 모든 객체의 최상위 부모는 `Object` 이므로 예외의 최상위 부모도 `Object` 이다.
* `Throwable` : 최상위 예외이다. 하위에 `Exception` 과 `Error` 가 있다.
* `Error` : 메모리 부족이나 심각한 시스템 오류와 같이 애플리케이션에서 복구 불가능한 시스템 예외이다. 애플리케이션 개발자는 이 예외를 잡으려고 해서는 안된다.
    * 상위 예외를 `catch` 로 잡으면 그 하위 예외까지 함께 잡는다. 따라서 애플리케이션 로직에서는 `Throwable` 예외도 잡으면 안되는데, 앞서 이야기한 `Error` 예외도 함께 잡을 수 있기
      때문이다. 애플리케이션 로직은 이런 이유로 `Exception` 부터 필요한 예외로 생각하고 잡으면 된다. 참고로 `Error` 도 언체크 예외이다.
* `Exception` : 체크 예외. 애플리케이션 로직에서 사용할 수 있는 실질적인 최상위 예외이다. `Exception` 과 그 하위 예외는 모두 컴파일러가 체크하는 체크 예외이다. 단
  `RuntimeException` 은 예외로 한다.
* `RuntimeException` : 언체크 예외, 런타임 예외
    * 컴파일러가 체크하지 않는 언체크 예외이다.
    * `RuntimeException` 과 그 자식 예외는 모두 언체크 예외이다.
    * `RuntimeException` 의 이름을 따라서 `RuntimeException` 과 그 하위 언체크 예외를 **런타임 예외**라고 많이 부른다. 여기서도 앞으로는 런타임 예외로 종종 부르겠다.

### 예외 기본 규칙

<img width="931" alt="Screenshot 2024-09-25 at 22 35 58" src="https://github.com/user-attachments/assets/44c8a319-8204-4257-908f-e05dc701a680">

**예외에 대해서는 2가지 기본 규칙을 기억하자.**

1. 예외는 잡아서 처리하거나 던져야 한다.
2. 예외를 잡거나 던질 때 지정한 예외뿐만 아니라 그 예외의 자식들도 함께 처리된다.
    * 예를 들어서 `Exception` 을 `catch` 로 잡으면 그 하위 예외들도 모두 잡을 수 있다.
    * 예를 들어서 `Exception` 을 `throws` 로 던지면 그 하위 예외들도 모두 던질 수 있다.

**참고: 예외를 처리하지 못하고 계속 던지면 어떻게 될까?**

자바 `main()` 쓰레드의 경우 예외 로그를 출력하면서 시스템이 종료된다.

웹 애플리케이션의 경우 여러 사용자의 요청을 처리하기 때문에 하나의 예외 때문에 시스템이 종료되면 안된다.

WAS가 해당 예외를 받아서 처리하는데, 주로 사용자에게 개발자가 지정한, 오류 페이지를 보여준다.

**체크 예외의 장단점**
체크 예외는 예외를 잡아서 처리할 수 없을 때, 예외를 밖으로 던지는 `throws 예외` 를 필수로 선언해야 한다.

그렇지 않으면 컴파일 오류가 발생한다. 이것 때문에 장점과 단점이 동시에 존재한다.

장점: 개발자가 실수로 예외를 누락하지 않도록 컴파일러를 통해 문제를 잡아주는 훌륭한 안전 장치이다.

단점: 하지만 실제로는 개발자가 모든 체크 예외를 반드시 잡거나 던지도록 처리해야 하기 때문에, 너무 번거로운 일이 된다. 크게 신경쓰고 싶지 않은 예외까지 모두 챙겨야 한다. 추가로 의존관계에 따른 단점도 있다.

### 언체크 예외 기본 이해

`RuntimeException` 과 그 하위 예외는 언체크 예외로 분류된다.

언체크 예외는 말 그대로 컴파일러가 예외를 체크하지 않는다는 뜻이다.

언체크 예외는 체크 예외와 기본적으로 동일하다.

차이가 있다면 예외를 던지는 `throws` 를 선언하지 않고, 생략 할 수 있다.

이 경우 자동으로 예외를 던진다.

**체크 예외 VS 언체크 예외**

* 체크 예외: 예외를 잡아서 처리하지 않으면 항상 `throws` 에 던지는 예외를 선언해야 한다.
* 언체크 예외: 예외를 잡아서 처리하지 않아도 `throws` 를 생략할 수 있다.

**언체크 예외의 장단점**

언체크예외는 예외를 잡아서 처리할 수 없을때, 예외를 밖으로 던지는 `throws 예외` 를 생략할 수 있다.

이것때문에 장점과 단점이 동시에 존재한다.

장점: 신경쓰고 싶지 않은 언체크 예외를 무시할 수 있다. 체크 예외의 경우 처리할 수 없는 예외를 밖으로 던지려 면 항상 `throws 예외` 를 선언해야 하지만, 언체크 예외는 이 부분을 생략할 수 있다. 이후에
설명하겠지만, 신경 쓰고 싶지 않은 예외의 의존관계를 참조하지 않아도 되는 장점이 있다.

단점: 언체크 예외는 개발자가 실수로 예외를 누락할 수 있다. 반면에 체크 예외는 컴파일러를 통해 예외 누락을 잡아준다.

### 체크 예외 활용

그렇다면 언제 체크 예외를 사용하고 언제 언체크(런타임) 예외를 사용하면 좋을까?

**기본 원칙은 다음 2가지를 기억하자.**

* 기본적으로 언체크(런타임) 예외를 사용하자.
* 체크 예외는 비즈니스 로직상 의도적으로 던지는 예외에만 사용하자.
    * 이 경우 해당 예외를 잡아서 반드시 처리해야 하는 문제일 때만 체크 예외를 사용해야 한다. 예를 들어서 다 음과 같은 경우가 있다.
    * 체크 예외 예)
        * 계좌 이체 실패 예외
        * 결제시 포인트 부족 예외 로그인 ID, PW 불일치 예외
    * 물론 이 경우에도 100% 체크 예외로 만들어야 하는 것은 아니다. 다만 계좌 이체 실패처럼 매우 심각한 문제는 개발자가 실수로 예외를 놓치면 안된다고 판단할 수 있다. 이 경우 체크 예외로 만들어 두면
      컴파일러를 통해 놓친 예외를 인지할 수 있다.

**체크 예외의 문제점**

체크 예외는 컴파일러가 예외 누락을 체크해주기 때문에 개발자가 실수로 예외를 놓치는 것을 막아준다.

그래서 항상 명시적으로 예외를 잡아서 처리하거나, 처리할 수 없을 때는 예외를 던지도록 `method() throws 예외` 로 선언해야 한다.

지금까지 이야기를 들어보면 체크 예외가 런타임 예외보다 더 안전하고 좋아보이는데, 왜 체크 예외를 기본으로 사용하는 것이 문제가 될까?

<img width="695" alt="Screenshot 2024-09-25 at 23 02 22" src="https://github.com/user-attachments/assets/1185296c-39b0-4197-b387-fc0ea3408cd6">

리포지토리는 DB에 접근해서 데이터를 저장하고 관리한다. 여기서는 `SQLException` 체크 예외를 던진다.

`NetworkClient` 는 외부 네트워크에 접속해서 어떤 기능을 처리하는 객체이다.

여기서는 `ConnectException` 체크 예외를 던진다.

서비스는 리포지토리와 `NetworkClient` 를 둘다 호출한다.

따라서 두 곳에서 올라오는 체크 예외인 `SQLException` 과 `ConnectException` 을 처리해야 한다.

그런데 서비스는 이 둘을 처리할 방법을 모른다. `ConnectException` 처럼 연결이 실패하거나, `SQLException` 처럼 데이터베이스에서 발생하는 문제처럼 심각한 문제들은 대부분 애플리케이션 로직
에서 처리할 방법이 없다.

서비스는 `SQLException` 과 `ConnectException` 를 처리할 수 없으므로 둘다 밖으로 던진다.

체크 예외이기 때문에 던질 경우 다음과 같이 선언해야 한다.

`method() throws SQLException, ConnectException` 컨트롤러도 두 예외를 처리할 방법이 없다.

다음을 선언해서 예외를 밖으로 던진다.

`method() throws SQLException, ConnectException`

웹 애플리케이션이라면 서블릿의 오류 페이지나, 또는 스프링 MVC가 제공하는 `ControllerAdvice` 에서 이 런 예외를 공통으로 처리한다.

이런 문제들은 보통 사용자에게 어떤 문제가 발생했는지 자세히 설명하기가 어렵다.

그래서 사용자에게는 "서비스에 문제가 있습니다." 라는 일반적인 메시지를 보여준다. ("데이터베이스에 어떤 오류가 발생했어요" 라고 알려주어도 일반 사용자가 이해할 수 없다. 그리고 보안에도 문제가 될 수 있다.)

API라면 보통 HTTP 상태코드 500(내부 서버 오류)을 사용해서 응답을 내려준다.

이렇게 해결이 불가능한 공통 예외는 별도의 오류 로그를 남기고, 개발자가 오류를 빨리 인지할 수 있도록 메일, 알림(문자, 슬랙)등을 통해서 전달 받아야 한다.

예를 들어서 `SQLException` 이 잘못된 SQL을 작성 해서 발생했다면, 개발자가 해당 SQL을 수정해서 배포하기 전까지 사용자는 같은 문제를 겪게 된다.

**2가지 문제**

지금까지 설명한 예시와 코드를 보면 크게 2가지 문제를 알 수 있다.

1. 복구 불가능한 예외
2. 의존 관계에 대한 문제

**1. 복구 불가능한 예외**
대부분의 예외는 복구가 불가능하다. 일부 복구가 가능한 예외도 있지만 아주 적다.

`SQLException` 을 예를 들면 데이터베이스에 무언가 문제가 있어서 발생하는 예외이다.

SQL 문법에 문제가 있을 수 도 있고, 데이터베이스 자체에 뭔가 문제가 발생했을 수도 있다.

데이터베이스 서버가 중간에 다운 되었을 수도 있다.

이런 문제들은 대부분 복구가 불가능하다.

특히나 대부분의 서비스나 컨트롤러는 이런 문제를 해결할 수는 없다.

따라서 이런 문제들은 일관성 있게 공통으로 처리해야 한다.

오류 로그를 남기고 개발자가 해당 오류를 빠르게 인지하는 것이 필요하다.

서블릿 필터, 스프링 인터셉터, 스프링의 `ControllerAdvice` 를 사용하면 이런 부분을 깔끔하게 공통으로 해결할 수 있다.

**2. 의존 관계에 대한 문제**

체크 예외의 또 다른 심각한 문제는 예외에 대한 의존 관계 문제이다.

앞서 대부분의 예외는 복구 불가능한 예외라고 했다.

그런데 체크 예외이기 때문에 컨트롤러나 서비스 입장에서는 본인이 처리할 수 없어도 어쩔수 없이 `throws` 를 통해 던지는 예외를 선언해야한다.

**체크 예외 throws 선언**

```java
class Controller {

    public void request() throws SQLException, ConnectException {
        service.logic();
    }

}


class Service {

    public void logic() throws SQLException, ConnectException {
        repository.call();
        networkClient.call();
    }
}
```

`throws SQLException, ConnectException` 처럼 예외를 던지는 부분을 코드에 선언하는 것이 왜 문제가 될까?

바로 서비스, 컨트롤러에서 `java.sql.SQLException` 을 의존하기 때문에 문제가 된다.

향후 리포지토리를 JDBC 기술이 아닌 다른 기술로 변경한다면, `SQLException` 이 아니라 예를 들어서 `JPAException` 으로 예외가 변경된다면 어떻게 될까?

`SQLException` 에 의존하던 모든 서비스, 컨트롤러의 코드를 `JPAException` 에 의존하도록 고쳐야 한다.

서비스나 컨트롤러 입장에서는 어차피 본인이 처리할 수 도 없는 예외를 의존해야 하는 큰 단점이 발생하게 된다.

결과적으로 OCP, DI를 통해 클라이언트 코드의 변경 없이 대상 구현체를 변경할 수 있다는 장점이 체크예외 때문에 발목을 잡게 된다.

**정리**
처리할 수 있는 체크 예외라면 서비스나 컨트롤러에서 처리하겠지만, 지금처럼 데이터베이스나 네트워크 통신처럼 시스템 레벨에서 올라온 예외들은 대부분 복구가 불가능하다.

그리고 실무에서 발생하는 대부분의 예외들은 이 런 시스템 예외들이다.

문제는 이런 경우에 체크 예외를 사용하면 아래에서 올라온 복구 불가능한 예외를 서비스, 컨트롤러 같은 각각의 클래스가 모두 알고 있어야 한다.

그래서 불필요한 의존관계 문제가 발생하게 된다.

**throws Exception**

`SQLException` , `ConnectException` 같은 시스템 예외는 컨트롤러나 서비스에서는 대부분 복구가 불가능하고 처리할 수 없는 체크 예외이다.

따라서 다음과 같이 처리해주어야 한다.

```java
void method() throws SQLException, ConnectException {} 
```

그런데 다음과 같이 최상위 예외인 `Exception` 을 던져도 문제를 해결할 수 있다.

```java
void method() throws Exception {} 
```

이렇게 하면 `Exception` 은 물론이고 그 하위 타입인 `SQLException` , `ConnectException` 도 함께 던지게 된다.

코드가 깔끔해지는 것 같지만, `Exception` 은 최상위타입이므로 모든 체크 예외를 다 밖으로 던지는 문제가 발생한다.

결과적으로 체크 예외의 최상위 타입인 `Exception` 을 던지게 되면 다른 체크 예외를 체크할 수 있는 기능이 무효화 되고, 중요한 체크 예외를 다 놓치게 된다.

중간에 중요한 체크 예외가 발생해도 컴파일러는 `Exception` 을 던지기 때 문에 문법에 맞다고 판단해서 컴파일 오류가 발생하지 않는다.

이렇게 하면 모든 예외를 다 던지기 때문에 체크 예외를 의도한 대로 사용하는 것이 아니다.

따라서 꼭 필요한 경우가 아니면 이렇게 `Exception` 자체를 밖으로 던지는 것은 좋지 않은 방법이다.

### 언체크 예외 활용

<img width="696" alt="Screenshot 2024-09-25 at 23 18 34" src="https://github.com/user-attachments/assets/88577e4e-2bad-466a-b932-9e836a755081">

`SQLException` 을 런타임 예외인 `RuntimeSQLException` 으로 변환했다.
`ConnectException` 대신에 `RuntimeConnectException` 을 사용하도록 바꾸었다.
런타임 예외이기 때문에 서비스, 컨트롤러는 해당 예외들을 처리할 수 없다면 별도의 선언 없이 그냥 두면 된다.

**런타임 예외 - 대부분 복구 불가능한 예외**

시스템에서 발생한 예외는 대부분 복구 불가능 예외이다.

런타임 예외를 사용하면 서비스나 컨트롤러가 이런 복구 불가 능한 예외를 신경쓰지 않아도 된다.

물론 이렇게 복구 불가능한 예외는 일관성 있게 공통으로 처리해야 한다.

**런타임 예외 - 의존 관계에 대한 문제**

런타임 예외는 해당 객체가 처리할 수 없는 예외는 무시하면 된다.

따라서 체크 예외 처럼 예외를 강제로 의존하지 않아도 된다.

**런타임 예외 구현 기술 변경시 파급 효과**

<img width="687" alt="Screenshot 2024-09-25 at 23 27 44" src="https://github.com/user-attachments/assets/aec5b4aa-ad42-45de-9710-a41b027b1894">

런타임 예외를 사용하면 중간에 기술이 변경되어도 해당 예외를 사용하지 않는 컨트롤러, 서비스에서는 코드를 변 경하지 않아도 된다.

구현 기술이 변경되는 경우, 예외를 공통으로 처리하는 곳에서는 예외에 따른 다른 처리가 필요할 수 있다.

하지만 공통 처리하는 한곳만 변경하면 되기 때문에 변경의 영향 범위는 최소화 된다.

**정리**

처음 자바를 설계할 당시에는 체크 예외가 더 나은 선택이라 생각했다.

그래서 자바가 기본으로 제공하는 기능들에는 체크 예외가 많다.

그런데 시간이 흐르면서 복구 할 수 없는 예외가 너무 많아졌다.

특히 라이브러리를 점점 더 많이 사용 하면서 처리해야 하는 예외도 더 늘어났다.

체크 예외는 해당 라이브러리들이 제공하는 모든 예외를 처리할 수 없을 때 마다 `throws` 에 예외를 덕지덕지 붙어야 했다.

그래서 개발자들은 `throws Exception` 이라는 극단적?인 방법도 자주 사용하게 되었다.

물론 이 방법은 사용하면 안된다.

모든 예외를 던진다고 선언하는 것인데, 결과적으로 어떤 예외 를 잡고 어떤 예외를 던지는지 알 수 없기 때문이다.

체크 예외를 사용한다면 잡을 건 잡고 던질 예외는 명확하게 던지도록 선언해야 한다.

체크 예외의 이런 문제점 때문에 최근 라이브러리들은 대부분 런타임 예외를 기본으로 제공한다.

사실 위에서 예시로 설명한 JPA 기술도 런타임 예외를 사용한다.

스프링도 대부분 런타임 예외를 제공한다.

런타임 예외도 필요하면 잡을 수 있기 때문에 필요한 경우에는 잡아서 처리하고, 그렇지 않으면 자연스럽게 던지도록 둔다.

그리고 예외를 공통으로 처리하는 부분을 앞에 만들어서 처리하면 된다.

추가로 런타임 예외는 놓칠 수 있기 때문에 문서화가 중요하다.

**런타임 예외는 문서화**

런타임 예외는 문서화를 잘해야 한다.

또는 코드에 `throws 런타임예외` 을 남겨서 중요한 예외를 인지할 수 있게 해준다.

**JPA EntityManager**

```java
/**
 * Make an instance managed and persistent.
 * @param entity  entity instance
 * @throws EntityExistsException if the entity already exists.
 * @throws IllegalArgumentException if the instance is not an
 *         entity
 * @throws TransactionRequiredException if there is no transaction when
 *         invoked on a container-managed entity manager of that is of type
 *         <code>PersistenceContextType.TRANSACTION</code>
 */
public void persist(Object entity); 
```

예) 문서에 예외 명시

**스프링 JdbcTemplate**

```java
 /**
 * Issue a single SQL execute, typically a DDL statement.
 * @param sql static SQL to execute
 * @throws DataAccessException if there is any problem
 */
void execute(String sql) throws DataAccessException; 
```

예) `method() throws DataAccessException` 와 같이 문서화 + 코드에도 명시

런타임 예외도 `throws` 에 선언할 수 있다. 물론 생략해도 된다.

던지는 예외가 명확하고 중요하다면, 코드에 어떤 예외를 던지는지 명시되어 있기 때문에 개발자가 IDE를 통해서 예외를 확인하기가 편리하다.

물론 컨트롤러나 서비스에서 `DataAccessException` 을 사용하지 않는다면 런타임 예외이기 때문에 무시해도 된다.

### 예외 포함과 스택 트레이스

예외를 전환할 때는 꼭! 기존 예외를 포함해야 한다. 그렇지 않으면 스택 트레이스를 확인할 때 심각한 문제가 발생한다.

```java

@Test
void printEx() {
    Controller controller = new Controller();

    try {
        controller.request();
    } catch(Exception e) {
        //e.printStackTrace();
        log.info("ex", e);
    }
}
```

로그를 출력할 때 마지막 파라미터에 예외를 넣어주면 로그에 스택 트레이스를 출력할 수 있다.

예) `log.info("message={}", "message", ex)` , 여기에서 마지막에 `ex` 를 전달하는 것을 확인할 수 있다.

이렇게 하면 스택 트레이스에 로그를 출력할 수 있다.

예) `log.info("ex", ex)` 지금 예에서는 파라미터가 없기 때문에, 예외만 파라미터에 전달하면 스택 트레이스를 로그에 출력할 수 있다.

`System.out` 에 스택 트레이스를 출력하려면 `e.printStackTrace()` 를 사용하면 된다. 실무에서는 항상 로그를 사용해야 한다는 점을 기억하자.

**기존 예외를 포함하는 경우**

```java

public void call() {
    try {
        runSQL();
    } catch(SQLException e) {
        throw new RuntimeSQLException(e); //기존 예외(e) 포함
    }
}
```

**기존 예외를 포함하지 않는 경우**

```java
 public void call() {
    try {
        runSQL();
    } catch(SQLException e) {
        throw new RuntimeSQLException(); //기존 예외(e) 제외 
    }
}
```

```shell
 [Test worker] INFO hello.jdbc.exception.basic.UncheckedAppTest - ex
 hello.jdbc.exception.basic.UncheckedAppTest$RuntimeSQLException: null
     at  hello.jdbc.exception.basic.UncheckedAppTest$Repository.call(UncheckedAppTest.java:61)
     at hello.jdbc.exception.basic.UncheckedAppTest$Service.logic(UncheckedAppTest.java:45)
```

예외를 포함하지 않아서 기존에 발생한 `java.sql.SQLException` 과 스택 트레이스를 확인할 수 없다.

변환한 `RuntimeSQLException` 부터 예외를 확인할 수 있다. 만약 실제 DB에 연동했다면 DB에서 발생한 예외를 확인할 수 없는 심각한 문제가 발생한다.

## 스프링의 예외처리 문제 해결

### 체크 예외와 인터페이스

서비스 계층은 가급적 특정 구현 기술에 의존하지 않고, 순수하게 유지하는 것이 좋다. 이렇게 하려면 예외에 대한 의존도 함께 해결해야한다.

예를 들어서 서비스가 처리할 수 없는 `SQLException` 에 대한 의존을 제거하려면 어떻게 해야할까?

서비스가 처리할 수 없으므로 리포지토리가 던지는 `SQLException` 체크 예외를 런타임 예외로 전환해서 서비스 계 층에 던지자.

이렇게 하면 서비스 계층이 해당 예외를 무시할 수 있기 때문에, 특정 구현기술에 의존하는 부분을 제거하 고 서비스 계층을 순수하게 유지할 수 있다.

<img width="922" alt="Screenshot 2024-09-26 at 22 56 40" src="https://github.com/user-attachments/assets/aec3bed8-07cf-4fdd-b124-d61b33fb988b">

이렇게 인터페이스를 도입하면 `MemberService` 는 `MemberRepository` 인터페이스에만 의존하면 된다.

이제 구현 기술을 변경하고 싶으면 DI를 사용해서 `MemberService` 코드의 변경 없이 구현 기술을 변경할 수 있다.

**MemberRepository 인터페이스**

```java
package hello.jdbc.repository;

import hello.jdbc.domain.Member;

public interface MemberRepository {

    Member save(Member member);

    Member findById(String memberId);

    void update(String memberId, int money);

    void delete(String memberId);
}
```

특정 기술에 종속되지 않는 순수한 인터페이스이다. 이 인터페이스를 기반으로 특정 기술을 사용하는 구현체를 만들면 된다.

**체크 예외와 인터페이스**

잠깐? 기존에는 왜 이런 인터페이스를 만들지 않았을까?

사실 다음과 같은 문제가 있기 때문에 만들지 않았다.

왜냐하면 `SQLException` 이 체크 예외이기 때문이다.

여기서 체크 예외가 또 발목을 잡는다.

체크 예외를 사용하려면 인터페이스에도 해당 체크 예외가 선언 되어 있어야 한다.

예를 들면 다음과 같은 코드가 된다.

**체크 예외 코드에 인터페이스 도입시 문제점 - 인터페이스**

```java
package hello.jdbc.repository;

import hello.jdbc.domain.Member;

import java.sql.SQLException;

public interface MemberRepositoryEx {

    Member save(Member member) throws SQLException;

    Member findById(String memberId) throws SQLException;

    void update(String memberId, int money) throws SQLException;

    void delete(String memberId) throws SQLException;
}
```

인터페이스의 메서드에 `throws SQLException` 이 있는 것을 확인할 수 있다.

**체크 예외 코드에 인터페이스 도입시 문제점 - 구현 클래스**

```java

@Slf4j
public class MemberRepositoryV3 implements MemberRepositoryEx {

    public Member save(Member member) throws SQLException {
        String sql = "insert into member(member_id, money) values(?, ?)";
    }
}
```

인터페이스의 구현체가 체크 예외를 던지려면, 인터페이스 메서드에 먼저 체크 예외를 던지는 부분이 선언 되어 있어야 한다.

그래야 구현 클래스의 메서드도 체크 예외를 던질 수 있다.

쉽게 이야기 해서 `MemberRepositoryV3` 가 `throws SQLException` 를 하려면 `MemberRepositoryEx` 인터페이스에도 `throws SQLException` 이 필요하다.

참고로 구현 클래스의 메서드에 선언할 수 있는 예외는 부모 타입에서 던진 예외와 같거나 하위 타입이어야 한다.

예를 들어서 인터페이스 메서드에 `throws Exception` 를 선언하면, 구현 클래스 메서드에 `throws SQLException` 는 가능하다.

`SQLException` 은 `Exception` 의 하위 타입이기 때문이다.

**특정 기술에 종속되는 인터페이스**

구현 기술을 쉽게 변경하기 위해서 인터페이스를 도입하더라도 `SQLException` 과 같은 특정 구현 기술에 종속적인 체크 예외를 사용하게 되면 인터페이스에도 해당 예외를 포함해야 한다.

하지만 이것은 우리가 원하던 순수한 인터페이스가 아니다. JDBC 기술에 종속적인 인터페이스일 뿐이다.

인터페이스를 만드는 목적은 구현체를 쉽게 변경하기 위함 인데, 이미 인터페이스가 특정 구현 기술에 오염이 되어 버렸다.

향후 JDBC가 아닌 다른 기술로 변경한다면 인터페이스 자체를 변경해야 한다.

**런타임 예외와 인터페이스**

런타임 예외는 이런 부분에서 자유롭다. 인터페이스에 런타임 예외를 따로 선언하지 않아도 된다.

따라서 인터페이스가 특정 기술에 종속적일 필요가 없다.

### 데이터 접근 예외 직접 만들기

데이터베이스 오류에 따라서 특정 예외는 복구하고 싶을 수 있다.

예를 들어서 회원 가입시 DB에 이미 같은 ID가 있으면 ID 뒤에 숫자를 붙여서 새로운 ID를 만들어야 한다고 가정해보자.

ID를 `hello` 라고 가입 시도 했는데, 이미 같은 아이디가 있으면 `hello12345` 와 같이 뒤에 임의의 숫자를 붙여서 가입하는 것이다.

데이터를 DB에 저장할 때 같은 ID가 이미 데이터베이스에 저장되어 있다면, 데이터베이스는 오류 코드를 반환하고, 이 오류 코드를 받은 JDBC 드라이버는 `SQLException` 을 던진다.

그리고 `SQLException` 에는 데이터베이스가 제공 하는 `errorCode` 라는 것이 들어있다.

<img width="942" alt="Screenshot 2024-09-26 at 23 22 39" src="https://github.com/user-attachments/assets/56554798-1728-421d-b726-f98f17b4c6ac">

**H2 데이터베이스의 키 중복 오류 코드**

```
e.getErrorCode() ==23505 
```

`SQLException` 내부에 들어있는 `errorCode` 를 활용하면 데이터베이스에서 어떤 문제가 발생했는지 확인할 수 있다.

**H2 데이터베이스 예**

* `23505` : 키 중복 오류
* `42000` : SQL 문법 오류

참고로 같은 오류여도 각각의 데이터베이스마다 정의된 오류 코드가 다르다. 따라서 오류 코드를 사용할 때는 데이터베이스 메뉴얼을 확인해야 한다.

**예) 키 중복 오류 코드**

* H2 DB: `23505`
* MySQL: `1062`

**H2 데이터베이스 오류 코드 참고**

https://www.h2database.com/javadoc/org/h2/api/ErrorCode.html

서비스 계층에서는 예외 복구를 위해 키 중복 오류를 확인할 수 있어야 한다.

그래야 새로운 ID를 만들어서 다시 저장을 시도할 수 있기 때문이다.

이러한 과정이 바로 예외를 확인해서 복구하는 과정이다.

리포지토리는 `SQLException` 을 서비스 계층에 던지고 서비스 계층은 이 예외의 오류 코드를 확인해서 키 중복 오류(`23505` )인 경우 새로운 ID를 만들 어서 다시 저장하면 된다.

그런데 `SQLException` 에 들어있는 오류 코드를 활용하기 위해 `SQLException` 을 서비스 계층으로 던지게 되면, 서비스 계층이 `SQLException` 이라는 JDBC 기술에 의존하게 되면서,
지금까지 우리가 고민했던 서비스 계층의 순수 성이 무너진다.

이 문제를 해결하려면 앞서 배운 것 처럼 리포지토리에서 예외를 변환해서 던지면 된다.

`SQLException` -> `MyDuplicateKeyException`

기존에 사용했던 `MyDbException` 을 상속받아서 의미있는 계층을 형성한다.

이렇게하면 데이터베이스 관련 예 외라는 계층을 만들 수 있다.

그리고 이름도 `MyDuplicateKeyException` 이라는 이름을 지었다.

이 예외는 데이터 중복의 경우에만 던져야 한다.

이 예외는 우리가 직접 만든 것이기 때문에, JDBC나 JPA 같은 특정 기술에 종속적이지 않다.

따라서 이 예외를 사용하더라도 서비스 계층의 순수성을 유지할 수 있다. (향후 JDBC에서 다른 기술로 바꾸어도 이 예외는 그대로 유지할 수 있다.)

**남은 문제**

SQL ErrorCode는 각각의 데이터베이스 마다 다르다.

결과적으로 데이터베이스가 변경될 때 마다 ErrorCode 도 모두 변경해야 한다.

예) 키 중복 오류 코드

H2: `23505`

MySQL: `1062`

데이터베이스가 전달하는 오류는 키 중복 뿐만 아니라 락이 걸린 경우, SQL 문법에 오류 있는 경우 등등 수십 수 백가지 오류 코드가 있다.

이 모든 상황에 맞는 예외를 지금처럼 다 만들어야 할까? 추가로 앞서 이야기한 것 처럼 데이터베이스마다 이 오류 코드는 모두 다르다.

### 스프링 예외 추상화 이해

스프링은 앞서 설명한 문제들을 해결하기 위해 데이터 접근과 관련된 예외를 추상화해서 제공한다.

<img width="692" alt="Screenshot 2024-09-26 at 23 42 11" src="https://github.com/user-attachments/assets/62906938-99e5-4ed1-8dbf-64c00a7a591e">

스프링은 데이터 접근 계층에 대한 수십 가지 예외를 정리해서 일관된 예외 계층을 제공한다.

각각의 예외는 특정 기술에 종속적이지 않게 설계되어 있다.

따라서 서비스 계층에서도 스프링이 제공하는 예외를 사용하면 된다.

예를 들어서 JDBC 기술을 사용하든, JPA 기술을 사용하든 스프링이 제공하는 예외를 사용하면 된다.

JDBC나 JPA를 사용할 때 발생하는 예외를 스프링이 제공하는 예외로 변환해주는 역할도 스프링이 제공한다.

참고로 그림을 단순화 하기 위해 일부 계층을 생략했다.

예외의 최고 상위는 `org.springframework.dao.DataAccessException` 이다.

그림에서 보는 것 처럼 런타임 예외를 상속 받았기 때문에 스프링이 제공하는 데이터 접근 계층의 모든 예외는 런타임 예외이다.

`DataAccessException` 은 크게 2가지로 구분하는데 `NonTransient` 예외와 `Transient` 예외이다.

`Transient` 는 일시적이라는 뜻이다.

`Transient` 하위 예외는 동일한 SQL을 다시 시도했을 때 성공할 가능성이 있다.

예를 들어서 쿼리 타임아웃, 락과 관련된 오류들이다.

이런 오류들은 데이터베이스 상태가 좋아지거나, 락이 풀렸을 때 다시 시도하면 성공할 수 도 있다.

`NonTransient` 는 일시적이지 않다는 뜻이다.

같은 SQL을 그대로 반복해서 실행하면 실패한다.

SQL 문법 오류, 데이터베이스 제약조건 위배 등이 있다.

참고: 스프링 메뉴얼에 모든 예외가 정리되어 있지는 않기 때문에 코드를 직접 열어서 확인해보는 것이 필요하다.

### 스프링이 제공하는 예외 변환기

스프링은 데이터베이스에서 발생하는 오류 코드를 스프링이 정의한 예외로 자동으로 변환해주는 변환기를 제공한다.

```java

@Test
void exceptionTranslator() {
    String sql = "select bad grammar";
    try {
        Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.executeQuery();
    } catch(SQLException e) {
        assertThat(e.getErrorCode()).isEqualTo(42122);
        //org.springframework.jdbc.support.sql-error-codes.xml
        SQLExceptionTranslator exTranslator = new SQLErrorCodeSQLExceptionTranslator(dataSource);
        //org.springframework.jdbc.BadSqlGrammarException
        DataAccessException resultEx = exTranslator.translate("select", sql, e);
        log.info("resultEx", resultEx);
        assertThat(resultEx.getClass()).isEqualTo(BadSqlGrammarException.class);
    }
}

```

`translate()` 메서드의 첫번째 파라미터는 읽을 수 있는 설명이고, 두번째는 실행한 sql, 마지막은 발생된 `SQLException` 을 전달하면 된다.

이렇게 하면 적절한 스프링 데이터 접근 계층의 예외로 변환해서 반환해준다.

예제에서는 SQL 문법이 잘못되었으므로 `BadSqlGrammarException` 을 반환하는 것을 확인할 수 있다.

눈에 보이는 반환 타입은 최상위 타입인 `DataAccessException` 이지만 실제로는 `BadSqlGrammarException` 예외가 반환된다.

마지막에 `assertThat()` 부분을 확인하자.

참고로 `BadSqlGrammarException` 은 최상위 타입인 `DataAccessException` 를 상속 받아서 만들어진다.

각각의 DB마다 SQL ErrorCode는 다르다.

그런데 스프링은 어떻게 각각의 DB가 제공하는 SQL ErrorCode까지 고려해서 예외를 변환할 수 있을까?

비밀은 바로 다음 파일에 있다.

**sql-error-codes.xml**

```xml

<bean id="H2" class="org.springframework.jdbc.support.SQLErrorCodes">
    <property name="badSqlGrammarCodes">
        <value>42000,42001,42101,42102,42111,42112,42121,42122,42132</value>
    </property>
    <property name="duplicateKeyCodes">
        <value>23001,23505</value>
    </property>
</bean>
<bean id="MySQL" class="org.springframework.jdbc.support.SQLErrorCodes">
<property name="badSqlGrammarCodes">
    <value>1054,1064,1146</value>
</property>
<property name="duplicateKeyCodes">
    <value>1062</value>
</property>
</bean>
```

`org.springframework.jdbc.support.sql-error-codes.xml`

스프링 SQL 예외 변환기는 SQL ErrorCode를 이 파일에 대입해서 어떤 스프링 데이터 접근 예외로 전환해야 할 지 찾아낸다.

예를 들어서 H2 데이터베이스에서 `42000` 이 발생하면 `badSqlGrammarCodes` 이기 때문에 `BadSqlGrammarException` 을 반환한다.

해당 파일을 확인해보면 10개 이상의 우리가 사용하는 대부분의 관계형 데이터베이스를 지원하는 것을 확인할 수 있다.

**정리**

스프링은 데이터 접근 계층에 대한 일관된 예외 추상화를 제공한다.

스프링은 예외 변환기를 통해서 `SQLException` 의 `ErrorCode` 에 맞는 적절한 스프링 데이터 접근 예외로 변환해준다.

만약 서비스, 컨트롤러 계층에서 예외 처리가 필요하면 특정 기술에 종속적인 `SQLException` 같은 예외를 직접 사용하는 것이 아니라, 스프링이 제공하는 데이터 접근 예외를 사용하면 된다.

스프링 예외 추상화 덕분에 특정 기술에 종속적이지 않게 되었다.

이제 JDBC에서 JPA같은 기술로 변경되어도 예외로 인한 변경을 최소화 할 수 있다.

향후 JDBC에서 JPA로 구현 기술을 변경하더라도, 스프링은 JPA 예외를 적절한 스프링 데이터 접근 예외로 변환해준다.

물론 스프링이 제공하는 예외를 사용하기 때문에 스프링에 대한 기술 종속성은 발생한다.

스프링에 대한 기술 종속성까지 완전히 제거하려면 예외를 모두 직접 정의하고 예외 변환도 직접 하면 되지만, 실용적인 방법은 아니다.

### 스프링 예외 추상화 적용

```java

@Slf4j
public class MemberRepositoryV4_2 implements MemberRepository {

    private final DataSource dataSource;
    private final SQLExceptionTranslator exTranslator;


    public MemberRepositoryV4_2(DataSource dataSource) {
        this.dataSource = dataSource;
        this.exTranslator = new SQLErrorCodeSQLExceptionTranslator(dataSource);
    }
}
```

**정리**

드디어 예외에 대한 부분을 깔끔하게 정리했다.

스프링이 예외를 추상화해준 덕분에, 서비스 계층은 특정 리포지토리의 구현 기술과 예외에 종속적이지 않게 되었다.

따라서 서비스 계층은 특정 구현 기술이 변경되어도 그대로 유지할 수 있게 되었다.

다시 DI를 제대로 활용할 수 있게 된 것이다.

추가로 서비스 계층에서 예외를 잡아서 복구해야 하는 경우, 예외가 스프링이 제공하는 데이터 접근 예외로 변경되어서 서비스 계층에 넘어오기 때문에 필요한 경우 예외를 잡아서 복구하면 된다.

### JDBC 반복 문제 해결 - JdbcTemplate

지금까지 서비스 계층의 순수함을 유지하기 위해 수 많은 노력을 했고, 덕분에 서비스 계층의 순수함을 유지하게 되었다.

이번에는 리포지토리에서 JDBC를 사용하기 때문에 발생하는 반복 문제를 해결해보자.

**JDBC 반복 문제**

* 커넥션 조회, 커넥션 동기화
* `PreparedStatement` 생성 및 파라미터 바인딩 쿼리 실행
* 결과 바인딩
* 예외 발생시 스프링 예외 변환기 실행
* 리소스 종료

리포지토리의 각각의 메서드를 살펴보면 상당히 많은 부분이 반복된다.

이런 반복을 효과적으로 처리하는 방법이 바로 템플릿 콜백 패턴이다.

스프링은 JDBC의 반복 문제를 해결하기 위해 `JdbcTemplate` 이라는 템플릿을 제공한다.

`JdbcTemplate` 에 대한 자세한 사용법은 뒤에서 설명하겠다.

지금은 전체 구조와, 이 기능을 사용해서 반복 코드를 제거할 수 있다는 것에 초점을 맞추자.

```java

@Slf4j
public class MemberRepositoryV5 implements MemberRepository {

    private final JdbcTemplate template;


    public MemberRepositoryV5(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }


    @Override
    public Member save(Member member) {
        String sql = "insert into member(member_id, money) values(?, ?)";
        template.update(sql, member.getMemberId(), member.getMoney());
        return member;
    }
}
```

`JdbcTemplate` 은 JDBC로 개발할 때 발생하는 반복을 대부분 해결해준다.

그 뿐만 아니라 지금까지 학습했던, **트랜 잭션을 위한 커넥션 동기화**는 물론이고, 예외 발생시 **스프링 예외 변환기**도 자동으로 실행해준다.

**정리**

서비스 계층의 순수성

트랜잭션 추상화 + 트랜잭션 AOP 덕분에 서비스 계층의 순수성을 최대한 유지하면서 서비스 계층에서 트랜잭션을 사용할 수 있다.

스프링이 제공하는 예외 추상화와 예외 변환기 덕분에, 데이터 접근 기술이 변경되어도 서비스 계층의 순수성을 유지하면서 예외도 사용할 수 있다.

서비스 계층이 리포지토리 인터페이스에 의존한 덕분에 향후 리포지토리가 다른 구현 기술로 변경되어도 서비스 계층을 순수하게 유지할 수 있다.

리포지토리에서 JDBC를 사용하는 반복 코드가 `JdbcTemplate` 으로 대부분 제거되었다.

















