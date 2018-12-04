package studio.inkode.recaptchaj;


import org.jetbrains.annotations.NotNull;
import studio.inkode.http.HttpRequest;
import studio.inkode.http.HttpUrl;
import studio.inkode.http.exception.UrlEncodingException;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created on 04.10.2018.
 *
 * @author Maxim Seredkin
 */
public class ReCaptchaImpl implements ReCaptcha {
    private static final String VERIFY_URI = "https://www.google.com/recaptcha/api/siteverify";

    private final String secret;

    public ReCaptchaImpl(@NotNull("secret must be not null") String secret) {
        if (secret.trim().isEmpty()) throw new IllegalArgumentException("secret must be not empty");

        this.secret = secret;
    }

    @Override
    public ReCaptchaResponse verify(@NotNull("response must be not null") String response) throws URISyntaxException, UrlEncodingException, IOException {
        if (response.trim().isEmpty()) throw new IllegalArgumentException("response must be not empty");

        return new HttpRequest(new HttpUrl(VERIFY_URI)
                                       .param("secret", secret)
                                       .param("response", response))
                .response(ReCaptchaResponseImpl.class);
    }

    @Override
    public ReCaptchaResponse verify(@NotNull("secret must be not null") String response,
                                    @NotNull("remote IP must be not null") String remoteIp) throws URISyntaxException, UrlEncodingException, IOException {
        if (response.trim().isEmpty()) throw new IllegalArgumentException("response must be not empty");

        return new HttpRequest(new HttpUrl(VERIFY_URI)
                                       .param("secret", secret)
                                       .param("response", response)
                                       .param("remoteIp", remoteIp))
                .response(ReCaptchaResponseImpl.class);
    }
}
