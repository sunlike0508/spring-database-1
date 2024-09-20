package hello.jdbc.connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Slf4j
public class ConnectionTest {

    @Test
    void driverManger() throws SQLException {
        Connection connection1 =
                DriverManager.getConnection(ConnectionConst.URL, ConnectionConst.USERNAME, ConnectionConst.PASSWORD);

        Connection connection2 =
                DriverManager.getConnection(ConnectionConst.URL, ConnectionConst.USERNAME, ConnectionConst.PASSWORD);

        log.info("connection1: {}", connection1);
        log.info("connection2: {}", connection2);
    }


    @Test
    void dataSourceDriverManager() throws SQLException {
        DataSource dataSource =
                new DriverManagerDataSource(ConnectionConst.URL, ConnectionConst.USERNAME, ConnectionConst.PASSWORD);

        Connection connection1 = dataSource.getConnection();
        Connection connection2 = dataSource.getConnection();

        log.info("connection1: {}", connection1);
        log.info("connection2: {}", connection2);

    }


    @Test
    void dataSourceConnectionPool() throws SQLException, InterruptedException {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(ConnectionConst.URL);
        dataSource.setUsername(ConnectionConst.USERNAME);
        dataSource.setPassword(ConnectionConst.PASSWORD);
        dataSource.setMaximumPoolSize(10);
        dataSource.setPoolName("MyPool");

        Connection connection1 = dataSource.getConnection();
        Connection connection2 = dataSource.getConnection();

        log.info("connection1: {}, {}", connection1, connection1.getClass());
        log.info("connection2: {}", connection2);

        Thread.sleep(1000);
    }
}
