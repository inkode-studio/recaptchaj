package studio.inkode.recaptchaj;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created on 10.10.2018.
 *
 * @author Maxim Seredkin
 */
public class WebReCaptchaResponse implements ReCaptchaResponse {
    private final boolean success;

    private final Date challengeTs;

    private final List<String> errorCodes;

    private final String hostname;

    @JsonCreator
    WebReCaptchaResponse(@JsonProperty("success") boolean success,
                         @JsonProperty("challenge_ts") Date challengeTs,
                         @JsonProperty("error-codes") List<String> errorCodes,
                         @JsonProperty("hostname") String hostname) {
        if (errorCodes == null) errorCodes = new ArrayList<>();

        this.success = success;
        this.challengeTs = challengeTs;
        this.errorCodes = errorCodes;
        this.hostname = hostname;
    }

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public Date getChallengeTs() {
        return challengeTs;
    }

    @Override
    public List<String> getErrorCodes() {
        return errorCodes;
    }

    public String getHostname() {
        return hostname;
    }
}
