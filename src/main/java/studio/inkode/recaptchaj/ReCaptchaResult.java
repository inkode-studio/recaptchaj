package studio.inkode.recaptchaj;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created on 02.10.2018.
 *
 * @author Maxim Seredkin
 */
public class ReCaptchaResult {
    private final ReCaptchaResponse response;

    public ReCaptchaResult(ReCaptchaResponse response) {this.response = response;}

    public boolean isSuccess() {
        return response.isSuccess();
    }

    public <T> T on(Function<ReCaptchaResponse, T> onSuccess,
                    Function<ReCaptchaResponse, T> onFail) {
        if (response.isSuccess()) {
            return onSuccess.apply(response);
        } else {
            return onFail.apply(response);
        }
    }

    public void on(Consumer<ReCaptchaResponse> onSuccess,
                   Consumer<ReCaptchaResponse> onFail) {
        if (response.isSuccess()) {
            onSuccess.accept(response);
        } else {
            onFail.accept(response);
        }
    }
}
