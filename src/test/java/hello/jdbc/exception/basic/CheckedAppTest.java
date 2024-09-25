package hello.jdbc.exception.basic;


import java.net.ConnectException;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class CheckedAppTest {

    @Test
    void test() throws SQLException, ConnectException {
        Controller controller = new Controller();

        controller.request();
    }


    static class Controller {

        Service service = new Service();


        void request() throws SQLException, ConnectException {
            service.logic();
        }
    }


    static class Service {

        NetworkClient networkClient = new NetworkClient();
        Repository repository = new Repository();


        public void logic() throws ConnectException, SQLException {
            networkClient.call();
            repository.call();
        }

    }


    static class NetworkClient {

        public void call() throws ConnectException {
            throw new ConnectException("net ex");
        }
    }


    static class Repository {

        public void call() throws SQLException {
            throw new SQLException("sql ex");
        }
    }
}
