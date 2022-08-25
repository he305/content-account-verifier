package com.github.he305.contentaccountverifier.domain.service;

import com.github.he305.contentaccountverifier.domain.exceptions.VerifierServiceException;
import com.github.he305.contentaccountverifier.domain.model.enums.Platform;
import com.github.he305.contentaccountverifier.domain.model.values.ContentAccountName;

public interface VerifierService {
    void verify(ContentAccountName contentAccountName) throws VerifierServiceException;

    Platform getType();
}
