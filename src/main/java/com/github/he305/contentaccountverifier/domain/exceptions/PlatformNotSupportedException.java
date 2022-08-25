package com.github.he305.contentaccountverifier.domain.exceptions;

import com.github.he305.contentaccountverifier.shared.exceptions.ContentAccountVerifierException;

public class PlatformNotSupportedException extends ContentAccountVerifierException {
    public PlatformNotSupportedException(String platform) {
        super(String.format("Platform %s not supported", platform));
    }
}
