package com.github.he305.contentaccountverifier.domain.service;

import com.github.he305.contentaccountverifier.domain.exceptions.ContentAccountInvalidException;

public interface ContentAccountService {
    void verify(String name, String platform) throws ContentAccountInvalidException;
}
