package hello.jdbc.repository;

import java.sql.SQLException;
import java.util.NoSuchElementException;
import hello.jdbc.domain.Member;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;

class MemberRepositoryV0Test {

    MemberRepositoryV0 memberRepository = new MemberRepositoryV0();


    @Test
    void save() throws SQLException {
        String memberID = "memberV5";

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
    }
}