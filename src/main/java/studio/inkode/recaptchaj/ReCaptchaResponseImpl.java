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
public class ReCaptchaResponseImpl implements ReCaptchaResponse {
    private final boolean success;

    private final Date challengeTs;

    private final List<String> errorCodes;

    private final String hostname;

    private final String appPackageName;

    @JsonCreator
    ReCaptchaResponseImpl(@JsonProperty("success") boolean success,
                          @JsonProperty("challenge_ts") Date challengeTs,
                          @JsonProperty("error-codes") List<String> errorCodes,
                          @JsonProperty("hostname") String hostname,
                          @JsonProperty("apk_package_name") String appPackageName) {
        this.success = success;
        this.challengeTs = challengeTs;
        this.errorCodes = errorCodes != null ? errorCodes : new ArrayList<>();
        this.hostname = hostname;
        this.appPackageName = appPackageName;
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

    @Override
    public String getHostname() {
        return hostname;
    }

    @Override
    public String getAppPackageName() {
        return appPackageName;
    }
}
