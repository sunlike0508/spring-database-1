package hello.jdbc.exception.basic;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class UnCheckedTest {

    @Test
    void exception() {
        Service service = new Service();

        service.callCatch();
    }


    static class MyUnCheckedException extends RuntimeException {

        public MyUnCheckedException(String message) {
            super(message);
        }
    }


    static class Service {

        Repository repository = new Repository();


        public void callCatch() {
            try {
                repository.call();
            } catch(MyUnCheckedException e) {
                log.info("예외처리 메시지 = {}", e.getMessage(), e);

            }
        }

    }


    static class Repository {

        public void call() {
            throw new MyUnCheckedException("ex");
        }
    }
}
