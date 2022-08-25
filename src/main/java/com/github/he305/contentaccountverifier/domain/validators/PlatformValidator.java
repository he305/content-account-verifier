package com.github.he305.contentaccountverifier.domain.validators;

import com.github.he305.contentaccountverifier.domain.exceptions.PlatformNotSupportedException;
import com.github.he305.contentaccountverifier.domain.model.enums.Platform;

public class PlatformValidator {
    private PlatformValidator() {
    }

    public static String validate(String strPlatform) throws PlatformNotSupportedException {
        try {
            Platform.valueOf(strPlatform);
            return strPlatform;
        } catch (IllegalArgumentException ex) {
            throw new PlatformNotSupportedException(strPlatform);
        }
    }
}
