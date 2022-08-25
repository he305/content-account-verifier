package com.github.he305.contentaccountverifier.application.services;

import com.github.he305.contentaccountverifier.application.utls.NetworkUtils;
import com.github.he305.contentaccountverifier.domain.exceptions.ContentAccountInvalidException;
import com.github.he305.contentaccountverifier.domain.model.enums.Platform;
import com.github.he305.contentaccountverifier.domain.model.values.ContentAccountName;
import com.github.he305.contentaccountverifier.domain.service.VerifierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class YoutubeVerifierServiceImpl implements VerifierService {
    private static final String SEARCH_URL = "http://youtube.com/channel/";
    private final NetworkUtils networkUtils;

    @Override
    public void verify(ContentAccountName contentAccountName) throws ContentAccountInvalidException {
        String url = SEARCH_URL + contentAccountName.getName();
        networkUtils.getSimple(url);
    }

    @Override
    public Platform getType() {
        return Platform.YOUTUBE;
    }
}
