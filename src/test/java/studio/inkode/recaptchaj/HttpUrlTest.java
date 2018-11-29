package studio.inkode.recaptchaj;

import org.junit.jupiter.api.Test;
import studio.inkode.http.HttpUrl;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created on 29.11.2018.
 *
 * @author Maxim Seredkin
 */
class HttpUrlTest {
    @Test
    void testParam() throws Exception {
        // Assert
        HttpUrl httpUrl = new HttpUrl("http://test.local");

        // Act
        String result = httpUrl.param("param", "value of parameter")
                               .toString();

        // Assert
        assertThat(result).isEqualTo("http://test.local?param=value+of+parameter");
    }

    @Test
    void testParamWhenAddedTwoQueryParameters() throws Exception {
        // Assert
        HttpUrl httpUrl = new HttpUrl("http://test.local");

        // Act
        String result = httpUrl.param("first_param", "value of first parameter")
                               .param("second_param", "value of second parameter")
                               .toString();

        // Assert
        assertThat(result).isEqualTo("http://test.local?first_param=value+of+first+parameter&second_param=value+of+second+parameter");
    }

    @Test
    void testUri() throws MalformedURLException, URISyntaxException {
        // Assert
        HttpUrl httpUrl = new HttpUrl("http://test.local");

        // Act
        URI result = httpUrl.uri();

        // Assert
        assertThat(result).isEqualTo(URI.create("http://test.local"));
    }

    @Test
    void testUrl() throws MalformedURLException, URISyntaxException {
        // Assert
        HttpUrl httpUrl = new HttpUrl("http://test.local");

        // Act
        URL result = httpUrl.url();

        // Assert
        assertThat(result).isEqualTo(new URL("http://test.local"));
    }

    @Test
    void testToString() throws MalformedURLException, URISyntaxException {
        // Assert
        HttpUrl httpUrl = new HttpUrl("http://test.local");

        // Act
        String result = httpUrl.toString();

        // Assert
        assertThat(result).isEqualTo("http://test.local");
    }
}