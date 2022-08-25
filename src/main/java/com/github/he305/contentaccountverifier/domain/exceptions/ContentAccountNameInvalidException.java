package com.github.he305.contentaccountverifier.domain.exceptions;

import com.github.he305.contentaccountverifier.shared.exceptions.ContentAccountVerifierException;

public class ContentAccountNameInvalidException extends ContentAccountVerifierException {
    public ContentAccountNameInvalidException(String contentAccountName) {
        super(String.format("Content account name %s is invalid", contentAccountName));
    }
}
