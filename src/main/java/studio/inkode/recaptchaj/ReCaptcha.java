package studio.inkode.recaptchaj;

import org.jetbrains.annotations.NotNull;
import studio.inkode.http.exception.UrlEncodingException;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created on 02.10.2018.
 *
 * @author Maxim Seredkin
 */
public interface ReCaptcha {
    /**
     * Verify the User's Response
     *
     * @param response Required. The user response token provided by reCAPTCHA, verifying the user on your site.
     */
    ReCaptchaResult verify(@NotNull("secret must be not null") String response) throws URISyntaxException, UrlEncodingException, IOException;

    /**
     * Verify the User's Response
     *
     * @param response Required. The user response token provided by reCAPTCHA, verifying the user on your site.
     * @param remoteIp Optional. The user's IP address.
     */
    ReCaptchaResult verify(@NotNull("secret must be not null") String response,
                           @NotNull("remote IP must be not null") String remoteIp);
}
