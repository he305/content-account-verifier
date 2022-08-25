package com.github.he305.contentaccountverifier.domain.exceptions;

import com.github.he305.contentaccountverifier.shared.exceptions.ContentAccountVerifierException;

public class VerifierServiceException extends ContentAccountVerifierException {
    public VerifierServiceException(String message) {
        super(message);
    }
}
