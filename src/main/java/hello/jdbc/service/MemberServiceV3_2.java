package hello.jdbc.service;


import java.sql.SQLException;
import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@Slf4j
public class MemberServiceV3_2 {

    private final TransactionTemplate transactionTemplate;
    private final MemberRepositoryV3 memberRepository;


    public MemberServiceV3_2(PlatformTransactionManager transactionManager, MemberRepositoryV3 memberRepository) {
        this.transactionTemplate = new TransactionTemplate(transactionManager);
        this.memberRepository = memberRepository;
    }


    public void accountTransfer(String fromId, String toId, int money) {

        transactionTemplate.executeWithoutResult(status -> {
            try {
                bizLogic(fromId, toId, money);
            } catch(SQLException e) {
                throw new IllegalStateException(e);
            }
        });
    }


    private void bizLogic(String fromId, String toId, int money) throws SQLException {
        Member fromMember = memberRepository.findById(fromId);
        Member toMember = memberRepository.findById(toId);

        memberRepository.update(fromId, fromMember.getMoney() - money);

        validation(toMember);

        memberRepository.update(toId, toMember.getMoney() + money);
    }


    private void validation(Member toMember) {
        if(toMember.getMemberId().equals("ex")) {
            throw new IllegalStateException("이체 . 예외 발생");
        }
    }
}
