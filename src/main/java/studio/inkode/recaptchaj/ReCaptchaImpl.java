package studio.inkode.recaptchaj;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import studio.inkode.http.HttpClientErrorException;
import studio.inkode.http.HttpServerErrorException;
import studio.inkode.http.HttpUrl;
import studio.inkode.http.exception.UrlEncodingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;

/**
 * Created on 04.10.2018.
 *
 * @author Maxim Seredkin
 */
public class ReCaptchaImpl implements ReCaptcha {
    private static final String VERIFY_URI = "https://www.google.com/recaptcha/api/siteverify";
    private static final String REQUEST_METHOD = "POST";

    private final String secret;

    public ReCaptchaImpl(@NotNull("secret must be not null") String secret) {
        if (secret.trim().isEmpty()) throw new IllegalArgumentException("secret must be not empty");

        this.secret = secret;
    }

    @Override
    public ReCaptchaResult verify(@NotNull("response must be not null") String response) throws URISyntaxException, UrlEncodingException, IOException {
        if (response.trim().isEmpty()) throw new IllegalArgumentException("response must be not empty");

        HttpURLConnection httpURLConnection = null;

        try {
            httpURLConnection = (HttpURLConnection) new HttpUrl(VERIFY_URI)
                    .param("secret", secret)
                    .param("response", response)
                    .url()
                    .openConnection();

            httpURLConnection.setRequestMethod(REQUEST_METHOD);
            httpURLConnection.setUseCaches(false);

            httpURLConnection.setAllowUserInteraction(true);

            httpURLConnection.setDoOutput(true);
            try (OutputStream outputStream = httpURLConnection.getOutputStream()) {
                outputStream.write(new byte[]{});
                outputStream.flush();
            }

            httpURLConnection.connect();

            int responseCode = httpURLConnection.getResponseCode();
            String responseMessage = httpURLConnection.getResponseMessage();

            if (responseCode / 100 == 5) throw new HttpServerErrorException(responseCode, responseMessage);
            if (responseCode / 100 == 4) throw new HttpClientErrorException(responseCode, responseMessage);

            ReCaptchaResponse reCaptchaResponse = extract(httpURLConnection);

            return new ReCaptchaResult(reCaptchaResponse);
        } catch (IOException | UrlEncodingException | URISyntaxException e) {
            e.printStackTrace();

            throw e;
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
    }

    private ReCaptchaResponse extract(HttpURLConnection con) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            ObjectMapper objectMapper = new ObjectMapper();

            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            JsonNode jsonNode = objectMapper.readTree(in);

            if (!jsonNode.isObject()) return null;

            if (jsonNode.hasNonNull("hostname")) {
                return objectMapper.readValue(jsonNode.traverse(), WebReCaptchaResponse.class);
            }

            if (jsonNode.hasNonNull("apk_package_name")) {
                return objectMapper.readValue(jsonNode.traverse(), AppReCaptchaResponse.class);
            }

            return objectMapper.readValue(jsonNode.traverse(), ErrorReCaptchaResponse.class);
        }
    }

    @Override
    public ReCaptchaResult verify(@NotNull("secret must be not null") String response,
                                  @NotNull("remote IP must be not null") String remoteIp) {
        return null;
    }
}
