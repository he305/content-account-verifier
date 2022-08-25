package com.github.he305.contentaccountverifier.application.exceptions;

import com.github.he305.contentaccountverifier.domain.exceptions.VerifierServiceException;

public class VerifierServiceNetworkException extends VerifierServiceException {
    public VerifierServiceNetworkException(String message) {
        super(message);
    }
}
