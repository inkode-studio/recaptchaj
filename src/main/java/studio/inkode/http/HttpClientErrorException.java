package studio.inkode.http;

/**
 * Created on 11.10.2018.
 *
 * @author Maxim Seredkin
 */
public class HttpClientErrorException extends RuntimeException {
    public HttpClientErrorException(int responseCode, String responseMessage) {}
}
