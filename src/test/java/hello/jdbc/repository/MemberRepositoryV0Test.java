package hello.jdbc.repository;

import java.sql.SQLException;
import hello.jdbc.domain.Member;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class MemberRepositoryV0Test {

    MemberRepositoryV0 memberRepository = new MemberRepositoryV0();


    @Test
    void save() throws SQLException {

        Member member = new Member("memberV0", 10000);

        memberRepository.save(member);
    }


    @Test
    void find() throws SQLException {
        Member member = memberRepository.findById("memberV0");

        assertThat(member.getMemberId()).isEqualTo("memberV0");
    }
}