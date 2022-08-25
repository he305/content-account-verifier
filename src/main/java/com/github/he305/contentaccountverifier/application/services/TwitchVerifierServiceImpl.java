package com.github.he305.contentaccountverifier.application.services;

import com.github.he305.contentaccountverifier.application.utls.NetworkUtils;
import com.github.he305.contentaccountverifier.domain.exceptions.VerifierServiceException;
import com.github.he305.contentaccountverifier.domain.model.enums.Platform;
import com.github.he305.contentaccountverifier.domain.model.values.ContentAccountName;
import com.github.he305.contentaccountverifier.domain.service.VerifierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class TwitchVerifierServiceImpl implements VerifierService {
    private static final String SEARCH_URL = "http://www.twitch.tv/";
    private static final Pattern EXISTING_ACCOUNT_PATTERN = Pattern.compile("(og:video:type)");
    private final NetworkUtils networkUtils;


    @Override
    public void verify(ContentAccountName contentAccountName) throws VerifierServiceException {
        String url = SEARCH_URL + contentAccountName.getName();
        String response = networkUtils.get(url);

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
