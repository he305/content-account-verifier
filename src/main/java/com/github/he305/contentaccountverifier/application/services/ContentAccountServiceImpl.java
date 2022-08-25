package com.github.he305.contentaccountverifier.application.services;

import com.github.he305.contentaccountverifier.domain.exceptions.ContentAccountInvalidException;
import com.github.he305.contentaccountverifier.domain.factories.VerifierServiceFactory;
import com.github.he305.contentaccountverifier.domain.model.ContentAccount;
import com.github.he305.contentaccountverifier.domain.service.ContentAccountService;
import com.github.he305.contentaccountverifier.domain.service.VerifierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContentAccountServiceImpl implements ContentAccountService {
    private final VerifierServiceFactory verifierServiceFactory;

    @Override
    public void verify(String name, String platform) throws ContentAccountInvalidException {
        ContentAccount contentAccount = new ContentAccount(name, platform);
        VerifierService verifierService = verifierServiceFactory.getService(contentAccount.getPlatform());
        verifierService.verify(contentAccount.getName());
    }
}
