package studio.inkode.http;

import lombok.extern.slf4j.Slf4j;
import studio.inkode.http.exception.UrlEncodingException;

import java.io.UnsupportedEncodingException;
import java.net.*;
import java.text.MessageFormat;

/**
 * Created on 29.11.2018.
 *
 * @author Maxim Seredkin
 */
@Slf4j
public class HttpUrl {
    private final URL url;

    public HttpUrl(String url) throws MalformedURLException, URISyntaxException {
        this(new URL(url));
    }

    public HttpUrl(URL url) throws URISyntaxException {
        this.url = url;
    }

    public HttpUrl(URI url) throws MalformedURLException {
        this.url = url.toURL();
    }

    public HttpUrl param(String name, String value) throws UrlEncodingException, MalformedURLException, URISyntaxException {
        String uriString = null;

        try {
            uriString = MessageFormat.format("{0}{1}{2}={3}",
                                             url.toString(),
                                             url.getQuery() == null ? "?" : "&",
                                             name,
                                             URLEncoder.encode(value, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
//            log.error(e.getLocalizedMessage(), e);

            throw new UrlEncodingException("value of parameter url encoding exception");
        }

        return new HttpUrl(uriString);
    }

    public URI uri() throws URISyntaxException {
        return url.toURI();
    }

    public URL url() throws MalformedURLException {
        return url;
    }

    @Override
    public String toString() {
        return url.toString();
    }
}
