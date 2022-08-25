package com.github.he305.contentaccountverifier.domain.exceptions;

import com.github.he305.contentaccountverifier.domain.model.ContentAccount;
import com.github.he305.contentaccountverifier.shared.exceptions.ContentAccountVerifierException;

public class ContentAccountInvalidException extends ContentAccountVerifierException {
    public ContentAccountInvalidException(ContentAccount contentAccount) {
        super(String.format("Verification of %s failed", contentAccount.toString()));
    }
}
