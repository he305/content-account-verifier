package com.github.he305.contentaccountverifier.application.services;

import com.github.he305.contentaccountverifier.domain.exceptions.VerifierServiceException;
import com.github.he305.contentaccountverifier.domain.model.enums.Platform;
import com.github.he305.contentaccountverifier.domain.model.values.ContentAccountName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TwitchVerifierServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private TwitchVerifierServiceImpl underTest;

    @Test
    void verify_valid() {
        ContentAccountName name = new ContentAccountName("UCs9_O1tRPMQTHQ-N_L6FU2g");
        String response = "<!DOCTYPE html><html class=\"tw-root--hover\"><head><meta charset=\"utf-8\"><title>Twitch</title><meta property=\"og:video:width\" content=\"620\"/><meta property=\"og:video:type\" content=\"text/html\"/><meta name=\"twitter:app:id:iphone\" content=\"id460177396\"/><meta name=\"twitter:app:url:iphone\" ";
        when(restTemplate.getForObject(Mockito.anyString(), eq(String.class))).thenReturn(response);
        assertDoesNotThrow(() -> underTest.verify(name));
    }

    @Test
    void verify_ResourceAccessException() {
        ContentAccountName name = new ContentAccountName("UCs9_O1tRPMQTHQ-N_L6FU2gs");
        when(restTemplate.getForObject(Mockito.anyString(), eq(String.class))).thenThrow(ResourceAccessException.class);
        assertThrows(VerifierServiceException.class, () -> underTest.verify(name));
    }

    @Test
    void verify_HttpClientErrorException() {
        ContentAccountName name = new ContentAccountName("UCs9_O1tRPMQTHQ-N_L6FU2gs");
        when(restTemplate.getForObject(Mockito.anyString(), eq(String.class))).thenThrow(HttpClientErrorException.class);
        assertThrows(VerifierServiceException.class, () -> underTest.verify(name));
    }

    @Test
    void verify_HttpServerErrorException() {
        ContentAccountName name = new ContentAccountName("UCs9_O1tRPMQTHQ-N_L6FU2gs");
        when(restTemplate.getForObject(Mockito.anyString(), eq(String.class))).thenThrow(HttpServerErrorException.class);
        assertThrows(VerifierServiceException.class, () -> underTest.verify(name));
    }

    @Test
    void verify_nullResponse() {
        ContentAccountName name = new ContentAccountName("UCs9_O1tRPMQTHQ-N_L6FU2g");
        when(restTemplate.getForObject(Mockito.anyString(), eq(String.class))).thenReturn(null);
        assertThrows(VerifierServiceException.class, () -> underTest.verify(name));
    }

    @Test
    void verify_patternNotFound() {
        ContentAccountName name = new ContentAccountName("UCs9_O1tRPMQTHQ-N_L6FU2g");
        String response = "<!DOCTYPE html><html class=\"tw-root--hover\"><head><meta charset=\"utf-8\"><title>Twitch</title><meta property='og:site_name' content='Twitch'><meta property='og:title' content='Twitch'><meta property='og:description' content='Twitch is the world&#39;s leading video platform and community for gamers.'><meta property='og:image' content='https://static-cdn.jtvnw.net/ttv-static-metadata/twitch_logo3.jpg'><meta property='og:type' content='website'><meta property='twitter:site' content='@twitch'><link rel=\"icon\" type=\"image/png\" sizes=\"32x32\" href=\"https://static.twitchcdn.net/assets/favicon-32-e29e246c157142c94346.png\"><link rel=\"icon\" type=\"image/png\" sizes=\"16x16\" href=\"https://static.twitchcdn.net/assets/favicon-16-52e571ffea063af7a7f4.png\"><link rel=\"dns-prefetch\" href=\"https://api.twitch.tv/\"><link rel=\"dns-prefetch\" href=\"https://passport.twitch.tv/\"><link rel=\"dns-prefetch\" href=\"https://static-cdn.jtvnw.net/\"><link rel=\"preconnect\" href=\"https://api.twitch.tv/\"><link rel=\"preconnect\" href=\"https://static-cdn.jtvnw.net/\"><script>!function(){var e,t,n,i,r={passive:!0,capture:!0},a=new Date,o=function(){i=[],t=-1,e=null,f(addEventListener)},c=function(i,r){e||(e=r,t=i,n=new Date,f(removeEventListener),u())},u=function(){if(t>=0&&t<n-a){var r={entryType:\"first-input\",name:e.type,target:";
        when(restTemplate.getForObject(Mockito.anyString(), eq(String.class))).thenReturn(response);
        assertThrows(VerifierServiceException.class, () -> underTest.verify(name));
    }

    @Test
    void getType() {
        Platform expected = Platform.TWITCH;
        Platform actual = underTest.getType();
        assertEquals(expected, actual);
    }
}