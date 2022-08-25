package com.github.he305.contentaccountverifier.domain.validators;

import com.github.he305.contentaccountverifier.domain.exceptions.ContentAccountNameInvalidException;

public class ContentAccountNameValidator {
    private ContentAccountNameValidator() {
    }

    public static String validate(String contentAccountName) throws ContentAccountNameInvalidException {
        if (contentAccountName == null || contentAccountName.isBlank()) {
            throw new ContentAccountNameInvalidException(contentAccountName);
        }
        return contentAccountName;
    }
}
