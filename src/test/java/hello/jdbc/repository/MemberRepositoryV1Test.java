package hello.jdbc.repository;

import java.sql.SQLException;
import java.util.NoSuchElementException;
import com.zaxxer.hikari.HikariDataSource;
import static hello.jdbc.connection.ConnectionConst.PASSWORD;
import static hello.jdbc.connection.ConnectionConst.URL;
import static hello.jdbc.connection.ConnectionConst.USERNAME;
import hello.jdbc.domain.Member;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberRepositoryV1Test {

    private MemberRepositoryV1 memberRepository;


    @BeforeEach
    void setUp() {
        //DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        memberRepository = new MemberRepositoryV1(dataSource);
    }


    @Test
    void save() throws SQLException, InterruptedException {
        String memberID = "memberV6";

        Member member = new Member(memberID, 10000);

        memberRepository.save(member);

        Member findMember = memberRepository.findById(memberID);

        assertThat(member).isNotSameAs(findMember);
        assertThat(member).isEqualTo(findMember);

        memberRepository.update(member.getMemberId(), 20000);

        Member updateMember = memberRepository.findById(memberID);

        assertThat(updateMember.getMoney()).isEqualTo(20000);

        memberRepository.delete(member.getMemberId());

        assertThatThrownBy(() -> memberRepository.findById(memberID)).isInstanceOf(NoSuchElementException.class);

        Thread.sleep(1000);
    }
}