package studio.inkode.http.exception;

/**
 * Created on 29.11.2018.
 *
 * @author Maxim Seredkin
 */
public class UrlEncodingException extends Exception {
    public UrlEncodingException() {
    }

    public UrlEncodingException(String message) {
        super(message);
    }

    public UrlEncodingException(String message, Throwable cause) {
        super(message, cause);
    }

    public UrlEncodingException(Throwable cause) {
        super(cause);
    }

    public UrlEncodingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
