package com.github.he305.contentaccountverifier.application.services;

import com.github.he305.contentaccountverifier.domain.exceptions.ContentAccountInvalidException;
import com.github.he305.contentaccountverifier.domain.exceptions.VerifierServiceException;
import com.github.he305.contentaccountverifier.domain.factories.VerifierServiceFactory;
import com.github.he305.contentaccountverifier.domain.model.ContentAccount;
import com.github.he305.contentaccountverifier.domain.service.ContentAccountService;
import com.github.he305.contentaccountverifier.domain.service.VerifierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContentAccountServiceImpl implements ContentAccountService {
    private final VerifierServiceFactory verifierServiceFactory;

    @Override
    public void verify(String name, String platform) throws ContentAccountInvalidException {
        ContentAccount contentAccount = new ContentAccount(name, platform);
        VerifierService verifierService = verifierServiceFactory.getService(contentAccount.getPlatform());
        try {
            verifierService.verify(contentAccount.getName());
        } catch (VerifierServiceException ex) {
            log.info(ex.getMessage());
            throw new ContentAccountInvalidException(contentAccount);
        }
    }
}
