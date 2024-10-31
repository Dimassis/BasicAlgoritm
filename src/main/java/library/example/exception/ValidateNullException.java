package library.example.exception;

public class ValidateNullException extends RuntimeException {
    public ValidateNullException(String message) {
        super(message);
    }

    public ValidateNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidateNullException(Throwable cause) {
        super(cause);
    }

    public ValidateNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
