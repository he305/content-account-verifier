package com.github.he305.contentaccountverifier.domain.factories;

import com.github.he305.contentaccountverifier.domain.model.enums.Platform;
import com.github.he305.contentaccountverifier.domain.service.VerifierService;

public interface VerifierServiceFactory {
    VerifierService getService(Platform platform);
}
