package com.github.he305.contentaccountverifier.application.services;

import com.github.he305.contentaccountverifier.application.exceptions.VerifierServiceNetworkException;
import com.github.he305.contentaccountverifier.application.utls.NetworkUtils;
import com.github.he305.contentaccountverifier.domain.model.enums.Platform;
import com.github.he305.contentaccountverifier.domain.model.values.ContentAccountName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class VKPlayVerifierServiceImplTest {

    @Mock
    private NetworkUtils networkUtils;

    @InjectMocks
    private VKPlayVerifierServiceImpl underTest;

    @Test
    void verify_error() {
        ContentAccountName name = new ContentAccountName("test");
        String url = "https://api.vkplay.live/v1/blog/test/public_video_stream";

        doThrow(VerifierServiceNetworkException.class).when(networkUtils).getSimple(url);
        assertThrows(VerifierServiceNetworkException.class, () -> underTest.verify(name));
    }

    @Test
    void verify_valid() {
        ContentAccountName name = new ContentAccountName("test");
        assertDoesNotThrow(() -> underTest.verify(name));
    }

    @Test
    void getType() {
        Platform expected = Platform.VKPLAY;
        Platform actual = underTest.getType();

        assertEquals(expected, actual);
    }
}