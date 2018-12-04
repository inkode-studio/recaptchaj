package studio.inkode.recaptchaj;

import java.util.Date;
import java.util.List;

/**
 * Created on 11.10.2018.
 *
 * @author Maxim Seredkin
 */
public interface ReCaptchaResponse {
    boolean isSuccess();

    Date getChallengeTs();

    List<String> getErrorCodes();

    String getAppPackageName();

    String getHostname();
}
