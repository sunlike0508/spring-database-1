package hello.jdbc.repository.ex;

public class MyDBException extends RuntimeException {

    public MyDBException() {}


    public MyDBException(String message) {
        super(message);
    }


    public MyDBException(Throwable cause) {
        super(cause);
    }


    public MyDBException(String message, Throwable cause) {
        super(message, cause);
    }
}
