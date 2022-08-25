package com.github.he305.contentaccountverifier.application.utls;

import com.github.he305.contentaccountverifier.application.exceptions.VerifierServiceNetworkException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class NetworkUtilsImpl implements NetworkUtils {
    private final RestTemplate restTemplate;

    @Override
    public String get(String url) {
        String response;
        try {
            response = restTemplate.getForObject(url, String.class);
        } catch (ResourceAccessException | HttpClientErrorException | HttpServerErrorException ex) {
            throw new VerifierServiceNetworkException(ex.getMessage());
        }

        if (response == null) {
            throw new VerifierServiceNetworkException("Response is null");
        }

        return response;
    }

    @Override
    public void getSimple(String url) {
        get(url);
    }
}
