package studio.inkode.recaptchaj;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created on 11.10.2018.
 *
 * @author Maxim Seredkin
 */
public interface ReCaptchaResponse {
    default ReCaptchaResponse getInstance(boolean success,
                                          Date challengeTs,
                                          List<String> errorCodes,
                                          String hostname,
                                          String appPackageName) {
        if (errorCodes == null) errorCodes = new ArrayList<>();

        if (appPackageName == null &&
            hostname != null && !hostname.trim().isEmpty()) {
            return new WebReCaptchaResponse(success, challengeTs, errorCodes, hostname);
        }

        if (hostname == null &&
            appPackageName != null && !appPackageName.trim().isEmpty()) {
            return new AppReCaptchaResponse(success, challengeTs, errorCodes, appPackageName);
        }

        throw new IllegalArgumentException();
    }

    public boolean isSuccess();

    public Date getChallengeTs();

    public List<String> getErrorCodes();
}
