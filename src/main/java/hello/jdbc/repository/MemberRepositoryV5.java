package hello.jdbc.repository;


import javax.sql.DataSource;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 * 트랜잭션 매니저
 */
@Slf4j
public class MemberRepositoryV5 implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;


    public MemberRepositoryV5(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Member save(Member member) {
        String sql = "insert into member(member_id, money) values(?, ?)";

        jdbcTemplate.update(sql, member.getMemberId(), member.getMoney());

        return member;
    }


    @Override
    public Member findById(String memberId) {
        String sql = "select * from member where member_id = ?";

        Member member = jdbcTemplate.queryForObject(sql, memberRowMapper(), memberId);

        return member;
    }


    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setMemberId(rs.getString("member_id"));
            member.setMoney(rs.getInt("money"));
            return member;
        };
    }


    @Override
    public void update(String memberId, int money) {

        String sql = "update member set money = ? where member_id = ?";

        jdbcTemplate.update(sql, money, memberId);
    }


    @Override
    public void delete(String memberId) {
        String sql = "delete from member where member_id = ?";

        jdbcTemplate.update(sql, memberId);
    }
}
