package com.github.he305.contentaccountverifier.application.services;

import com.github.he305.contentaccountverifier.application.utls.NetworkUtils;
import com.github.he305.contentaccountverifier.domain.exceptions.VerifierServiceException;
import com.github.he305.contentaccountverifier.domain.model.enums.Platform;
import com.github.he305.contentaccountverifier.domain.model.values.ContentAccountName;
import com.github.he305.contentaccountverifier.domain.service.VerifierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoodgameVerifierServiceImpl implements VerifierService {
    private static final String SEARCH_URL = "http://goodgame.ru/channel/";
    private final NetworkUtils networkUtils;

    @Override
    public void verify(ContentAccountName contentAccountName) throws VerifierServiceException {
        String url = SEARCH_URL + contentAccountName.getName();
        networkUtils.getSimple(url);
    }

    @Override
    public Platform getType() {
        return Platform.GOODGAME;
    }
}
