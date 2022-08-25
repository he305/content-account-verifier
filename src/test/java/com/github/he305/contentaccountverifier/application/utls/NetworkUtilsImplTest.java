package com.github.he305.contentaccountverifier.application.utls;

import com.github.he305.contentaccountverifier.domain.exceptions.VerifierServiceException;
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
class NetworkUtilsImplTest {
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private NetworkUtilsImpl underTest;

    @Test
    void getSimple_valid() {
        String url = "asd";
        String response = "<!DOCTYPE html><html class=\"tw-root--hover\"><head><meta charset=\"utf-8\"><title>Twitch</title><meta property=\"og:video:width\" content=\"620\"/><meta property=\"og:video:type\" content=\"text/html\"/><meta url=\"twitter:app:id:iphone\" content=\"id460177396\"/><meta url=\"twitter:app:url:iphone\" ";
        when(restTemplate.getForObject(Mockito.anyString(), eq(String.class))).thenReturn(response);
        assertDoesNotThrow(() -> underTest.getSimple(url));
    }

    @Test
    void get_valid() {
        String url = "asd";
        String response = "<!DOCTYPE html><html class=\"tw-root--hover\"><head><meta charset=\"utf-8\"><title>Twitch</title><meta property=\"og:video:width\" content=\"620\"/><meta property=\"og:video:type\" content=\"text/html\"/><meta url=\"twitter:app:id:iphone\" content=\"id460177396\"/><meta url=\"twitter:app:url:iphone\" ";
        when(restTemplate.getForObject(Mockito.anyString(), eq(String.class))).thenReturn(response);
        String actual = underTest.get(url);
        assertEquals(response, actual);
    }

    @Test
    void get_ResourceAccessException() {
        String url = "asd";
        when(restTemplate.getForObject(Mockito.anyString(), eq(String.class))).thenThrow(ResourceAccessException.class);
        assertThrows(VerifierServiceException.class, () -> underTest.get(url));
    }

    @Test
    void get_HttpClientErrorException() {
        String url = "asd";
        when(restTemplate.getForObject(Mockito.anyString(), eq(String.class))).thenThrow(HttpClientErrorException.class);
        assertThrows(VerifierServiceException.class, () -> underTest.get(url));
    }

    @Test
    void get_HttpServerErrorException() {
        String url = "asd";
        when(restTemplate.getForObject(Mockito.anyString(), eq(String.class))).thenThrow(HttpServerErrorException.class);
        assertThrows(VerifierServiceException.class, () -> underTest.get(url));
    }

    @Test
    void get_nullResponse() {
        String url = "asd";
        when(restTemplate.getForObject(Mockito.anyString(), eq(String.class))).thenReturn(null);
        assertThrows(VerifierServiceException.class, () -> underTest.get(url));
    }
}