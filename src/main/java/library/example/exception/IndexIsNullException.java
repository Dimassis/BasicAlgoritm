package library.example.exception;

public class IndexIsNullException extends RuntimeException {
    public IndexIsNullException(String message) {
        super(message);
    }

    public IndexIsNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public IndexIsNullException(Throwable cause) {
        super(cause);
    }

    public IndexIsNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
