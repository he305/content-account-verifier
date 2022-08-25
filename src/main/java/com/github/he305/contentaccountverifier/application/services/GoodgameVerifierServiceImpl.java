package com.github.he305.contentaccountverifier.application.services;

import com.github.he305.contentaccountverifier.domain.exceptions.VerifierServiceException;
import com.github.he305.contentaccountverifier.domain.model.enums.Platform;
import com.github.he305.contentaccountverifier.domain.model.values.ContentAccountName;
import com.github.he305.contentaccountverifier.domain.service.VerifierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GoodgameVerifierServiceImpl implements VerifierService {
    private static final String SEARCH_URL = "http://goodgame.ru/channel/";
    private final RestTemplate restTemplate;

    @Override
    public void verify(ContentAccountName contentAccountName) throws VerifierServiceException {
        String url = SEARCH_URL + contentAccountName.getName();
        try {
            restTemplate.getForObject(url, String.class);
        } catch (ResourceAccessException | HttpClientErrorException | HttpServerErrorException ex) {
            throw new VerifierServiceException(ex.getMessage());
        }
    }

    @Override
    public Platform getType() {
        return Platform.GOODGAME;
    }
}
