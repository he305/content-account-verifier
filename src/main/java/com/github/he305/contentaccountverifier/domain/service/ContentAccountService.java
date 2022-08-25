package com.github.he305.contentaccountverifier.domain.service;

import com.github.he305.contentaccountverifier.domain.exceptions.ContentAccountInvalidException;
import com.github.he305.contentaccountverifier.domain.model.ContentAccount;

public interface ContentAccountService {
    void verify(ContentAccount contentAccount) throws ContentAccountInvalidException;
}
