package com.github.he305.contentaccountverifier.domain.exceptions;

import com.github.he305.contentaccountverifier.domain.model.enums.Platform;
import com.github.he305.contentaccountverifier.shared.exceptions.ContentAccountVerifierException;

public class PlatformVerifierNotImplementedException extends ContentAccountVerifierException {
    public PlatformVerifierNotImplementedException(Platform platform) {
        super(String.format("Service verifier of %s not implemented", platform.name()));
    }
}
