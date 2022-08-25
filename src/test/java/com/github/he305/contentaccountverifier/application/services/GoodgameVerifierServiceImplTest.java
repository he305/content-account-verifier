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
class GoodgameVerifierServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private GoodgameVerifierServiceImpl underTest;

    @Test
    void verify_valid() {
        ContentAccountName name = new ContentAccountName("UCs9_O1tRPMQTHQ-N_L6FU2g");
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
    void getType() {
        Platform expected = Platform.GOODGAME;
        Platform actual = underTest.getType();
        assertEquals(expected, actual);
    }
}