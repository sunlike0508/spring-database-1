package hello.jdbc.service;

import java.sql.SQLException;
import static hello.jdbc.connection.ConnectionConst.PASSWORD;
import static hello.jdbc.connection.ConnectionConst.URL;
import static hello.jdbc.connection.ConnectionConst.USERNAME;
import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

class MemberServiceV1Test {

    public static final String MEMBER_A = "memberA";
    public static final String MEMBER_B = "memberB";
    public static final String MEMBER_EX = "ex";

    private MemberServiceV1 memberService;
    private MemberRepositoryV1 memberRepository;


    @BeforeEach
    void setUp() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        memberRepository = new MemberRepositoryV1(dataSource);
        memberService = new MemberServiceV1(memberRepository);
    }


    @Test
    void accountTransfer() throws SQLException {
        Member memberA = new Member(MEMBER_A, 10000);
        memberRepository.save(memberA);

        Member memberB = new Member(MEMBER_B, 10000);
        memberRepository.save(memberB);

        memberService.accountTransfer(memberA.getMemberId(), memberB.getMemberId(), 2000);

        Member findMemberA = memberRepository.findById(memberA.getMemberId());
        Member findMemberB = memberRepository.findById(memberB.getMemberId());

        assertThat(findMemberA.getMoney()).isEqualTo(8000);
        assertThat(findMemberB.getMoney()).isEqualTo(12000);
    }


    @Test
    void accountTransfer2() throws SQLException {
        Member memberA = new Member(MEMBER_A, 10000);
        memberRepository.save(memberA);

        Member memberEX = new Member(MEMBER_EX, 10000);
        memberRepository.save(memberEX);

        assertThatThrownBy(
                () -> memberService.accountTransfer(memberA.getMemberId(), memberEX.getMemberId(), 2000)).isInstanceOf(
                IllegalStateException.class);

        Member findMemberA = memberRepository.findById(memberA.getMemberId());
        Member findMemberEX = memberRepository.findById(memberEX.getMemberId());

        assertThat(findMemberA.getMoney()).isEqualTo(8000);
        assertThat(findMemberEX.getMoney()).isEqualTo(10000);
    }


    @AfterEach
    void tearDown() throws SQLException {
        memberRepository.delete(MEMBER_A);
        memberRepository.delete(MEMBER_B);
        memberRepository.delete(MEMBER_EX);
    }
}