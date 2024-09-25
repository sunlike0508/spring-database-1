package hello.jdbc.exception.basic;


import java.net.ConnectException;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class UnCheckedAppTest {

    @Test
    void test() {
        Controller controller = new Controller();

        controller.request();
    }


    @Test
    void printEx() {
        Controller controller = new Controller();

        try {
            controller.request();
        } catch(Exception e) {
            //e.printStackTrace();
            log.error("ex={},{}", e.getMessage(), e.getLocalizedMessage(), e);
        }
    }


    static class Controller {

        Service service = new Service();


        void request() {
            service.logic();
        }
    }


    static class Service {

        NetworkClient networkClient = new NetworkClient();
        Repository repository = new Repository();


        public void logic() {
            networkClient.call();
            repository.call();
        }

    }


    static class NetworkClient {

        public void call() {
            try {
                run();
            } catch(ConnectException e) {
                //throw new RuntimeConnectException();
                throw new RuntimeConnectException(e);
            }
        }


        public void run() throws ConnectException {
            throw new ConnectException("net ex");
        }
    }


    static class Repository {

        public void call() {
            try {
                run();
            } catch(SQLException e) {
                throw new RuntimeSQLException(e.getMessage());
            }
        }


        public void run() throws SQLException {
            throw new SQLException("sql ex");
        }
    }


    static class RuntimeSQLException extends RuntimeException {

        public RuntimeSQLException(String message) {
            super(message);
        }
    }


    static class RuntimeConnectException extends RuntimeException {

        public RuntimeConnectException() {
        }


        public RuntimeConnectException(Throwable e) {
            super(e);
        }
    }
}
