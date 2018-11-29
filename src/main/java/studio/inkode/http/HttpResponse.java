package studio.inkode.http;

import java.net.HttpURLConnection;

/**
 * Created on 11.10.2018.
 *
 * @author Maxim Seredkin
 */
public class HttpResponse {
    private final HttpURLConnection httpURLConnection;

    public HttpResponse(HttpURLConnection httpURLConnection) {this.httpURLConnection = httpURLConnection;}
}
