package hello.jdbc.repository.ex;

public class MyDuplicateKeyDBException extends RuntimeException {

    public MyDuplicateKeyDBException() {}


    public MyDuplicateKeyDBException(String message) {
        super(message);
    }


    public MyDuplicateKeyDBException(Throwable cause) {
        super(cause);
    }


    public MyDuplicateKeyDBException(String message, Throwable cause) {
        super(message, cause);
    }
}
