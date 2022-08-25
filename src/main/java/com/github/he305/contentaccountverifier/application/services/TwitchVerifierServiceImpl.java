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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class TwitchVerifierServiceImpl implements VerifierService {
    private static final String SEARCH_URL = "http://www.twitch.tv/";
    private static final Pattern EXISTING_ACCOUNT_PATTERN = Pattern.compile("(og:video:type)");
    private final RestTemplate restTemplate;


    @Override
    public void verify(ContentAccountName contentAccountName) throws VerifierServiceException {
        String url = SEARCH_URL + contentAccountName.getName();
        String response;
        try {
            response = restTemplate.getForObject(url, String.class);
        } catch (ResourceAccessException | HttpClientErrorException | HttpServerErrorException ex) {
            throw new VerifierServiceException(ex.getMessage());
        }

        if (response == null) {
            throw new VerifierServiceException("Response is null");
        }

        Matcher matcher = EXISTING_ACCOUNT_PATTERN.matcher(response);
        if (!matcher.find()) {
            throw new VerifierServiceException("Pattern not found");
        }
    }

    @Override
    public Platform getType() {
        return Platform.TWITCH;
    }
}
