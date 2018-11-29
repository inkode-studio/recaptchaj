package studio.inkode.recaptchaj;

import io.specto.hoverfly.junit.core.Hoverfly;
import io.specto.hoverfly.junit5.HoverflyExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import studio.inkode.http.exception.UrlEncodingException;

import java.net.URISyntaxException;

import static io.specto.hoverfly.junit.core.SimulationSource.dsl;
import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created on 23.11.2018.
 *
 * @author Maxim Seredkin
 */
@ExtendWith(HoverflyExtension.class)
class ReCaptchaImplTest {
    @Test
    void verify(Hoverfly hoverfly) throws URISyntaxException, UrlEncodingException {
        // Arrange
        hoverfly.simulate(dsl(service("www.google.com")
                                      .post("/recaptcha/api/siteverify")
                                      .queryParam("secret", "6LeIxAcTAAAAAGG-vFI1TnRWxMZNFuojJ4WifJWe")
                                      .queryParam("response", "test")
                                      .willReturn(success("{\"success\":true}", "application/json"))));

        // Act
        boolean result = new ReCaptchaImpl("6LeIxAcTAAAAAGG-vFI1TnRWxMZNFuojJ4WifJWe")
                .verify("test")
                .isSuccess();

        // Assert
        assertThat(result).isEqualTo(true);
    }

    @Test
    void verify1() {
    }
}