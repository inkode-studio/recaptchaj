package studio.inkode.recaptchaj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created on 04.10.2018.
 *
 * @author Maxim Seredkin
 */
class ReCaptchaImplIT {
    private String invalidSecret = "InVaLid";
    private String validSecret = "6LeIxAcTAAAAAGG-vFI1TnRWxMZNFuojJ4WifJWe";

    @Test
    void verifyByResponse() throws Exception {
        // Arrange
        String secret = validSecret;

        ReCaptcha reCaptcha = new ReCaptchaImpl(secret);

        // Act
        ReCaptchaResponse result = reCaptcha.verify("test");

        // Assert
        assertThat(result.isSuccess()).isTrue();
    }

    @Test
    void verifyByResponseWithInvalidSecret() throws Exception {
        // Arrange
        String secret = invalidSecret;

        ReCaptcha reCaptcha = new ReCaptchaImpl(secret);

        // Act
        ReCaptchaResponse result = reCaptcha.verify("test");

        // Assert
        assertThat(result.isSuccess()).isFalse();
    }

    @Test
    void verifyByResponseAndRemoteIp() {
    }
}