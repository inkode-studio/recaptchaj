package studio.inkode.http;

import org.jetbrains.annotations.NotNull;
import studio.inkode.http.exception.UrlEncodingException;
import studio.inkode.serialize.DefaultDeserializer;
import studio.inkode.serialize.Deserializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;

/**
 * Created on 29.11.2018.
 *
 * @author Maxim Seredkin
 */
public class HttpRequest {
    private final HttpURLConnection httpURLConnection;
    private final Deserializer deserializer;

    public HttpRequest(HttpUrl url) throws MalformedURLException, IOException, ProtocolException, UrlEncodingException {
        this(url, new DefaultDeserializer());
    }

    public HttpRequest(HttpUrl url,
                       @NotNull("deserializer must be not null") Deserializer deserializer) throws MalformedURLException, IOException, ProtocolException, UrlEncodingException {
        this.httpURLConnection = (HttpURLConnection) url.url().openConnection();

        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setUseCaches(false);

        httpURLConnection.setAllowUserInteraction(true);

        httpURLConnection.setDoOutput(true);
        try (OutputStream outputStream = httpURLConnection.getOutputStream()) {
            outputStream.write(new byte[]{});
            outputStream.flush();
        }

        this.deserializer = deserializer;
    }

    public <ObjectT> ObjectT response(Class<ObjectT> objectClass) throws IOException {
        httpURLConnection.connect();

        try (InputStream inputStream = httpURLConnection.getInputStream()) {
            return deserializer.deserialize(inputStream, objectClass);
        }
    }
}
