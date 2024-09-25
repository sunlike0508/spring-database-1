package hello.jdbc.exception.basic;


import lombok.extern.slf4j.Slf4j;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;

@Slf4j
class CheckedTest {

    @Test
    void exception() {
        Service service = new Service();

        service.callCatch();
    }


    @Test
    void checked_throw() {
        Service service = new Service();

        assertThatThrownBy(service::callThrow).isInstanceOf(MyCheckedException.class);
    }


    static class MyCheckedException extends Exception {

        public MyCheckedException(String message) {
            super(message);
        }
    }


    static class Service {

        Repository repository = new Repository();


        public void callCatch() {
            try {
                repository.call();
            } catch(MyCheckedException e) {
                log.info("예외처리 메시지 = {}", e.getMessage(), e);
            }
        }


        public void callThrow() throws MyCheckedException {
            repository.call();
        }
    }


    static class Repository {

        public void call() throws MyCheckedException {
            throw new MyCheckedException("ex");
        }
    }
}
